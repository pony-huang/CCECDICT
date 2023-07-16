package org.github.ponking66.ccecdit.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ProcessingContext;
import org.github.ponking66.ccecdit.DictionaryIcons;
import org.github.ponking66.ccecdit.Word;
import org.github.ponking66.ccecdit.WordManagerService;
import org.github.ponking66.ccecdit.cache.FrequencyWordCacheComponent;
import org.github.ponking66.ccecdit.settings.CodeCompletionSettings;
import org.github.ponking66.ccecdit.util.NotificationUtil;
import org.github.ponking66.ccecdit.util.WordUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author pony
 * @date 2023/7/13
 */
public class DictionaryCompletionProvider extends CompletionProvider<CompletionParameters> {

    private final Logger LOGGER = Logger.getInstance(DictionaryCompletionProvider.class);

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters,
                                  @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        Project project = parameters.getOriginalFile().getProject();
        // 尚未设置字典路径
        CodeCompletionSettings settings = ApplicationManager.getApplication().getService(CodeCompletionSettings.class);
        if (StringUtil.isEmpty(settings.getSqliteDictPath())) {
            NotificationUtil.notifyWarning(project, "尚未设置字典路径");
            return;
        }
        // 用户输入的前缀
        PrefixMatcher prefixMatcher = result.getPrefixMatcher();
        String prefix = prefixMatcher.getPrefix();
        // 如果用户输入的前缀是复合单词，获取最后一个单词并转换为小写。
        if (WordUtil.isCompositeNameByUnderline(prefix)) {
            prefix = WordUtil.findLastWord(prefix, true).toLowerCase(Locale.ROOT);
        } else if (WordUtil.isCompositeNameByOneAlphaUpperCase(prefix)) {
            prefix = WordUtil.findLastWord(prefix, false).toLowerCase(Locale.ROOT);
        }

        if (StringUtil.isEmpty(prefix)) {
            return;
        }
        FrequencyWordCacheComponent frequencyWordCacheComponent = FrequencyWordCacheComponent.getInstance();
        WordManagerService wordManagerService = ApplicationManager.getApplication().getService(WordManagerService.class);
        Map<String, Integer> cache = Objects.requireNonNull(frequencyWordCacheComponent.getState()).getCache();
        Set<String> keys = cache.keySet();
        List<Word> pairedWords = wordManagerService.searchWords(prefix, new ArrayList<>(keys), settings.getPairedWordCount());
        // 任何前缀变化都会重新开始补全。
        result.restartCompletionOnAnyPrefixChange();
        if (pairedWords.isEmpty()) {
            result.restartCompletionOnAnyPrefixChange();
            return;
        }

        final String prefixCopy = prefix;

        List<LookupElement> lookupElements = pairedWords.stream().peek(item -> {
                    Integer count = cache.get(item.getWord());
                    if (count != null) {
                        item.setFrequency(count);
                    }
                })
                .map(item -> PrioritizedLookupElement.withPriority(buildLookupElementBuilder(frequencyWordCacheComponent).apply(item), item.word.equals(prefixCopy) ? Double.MAX_VALUE : item.frequency * 1.0))
                .collect(Collectors.toList());

        CompletionResultSet resultSet = result.withPrefixMatcher(new WordPrefixMatcher(prefix));
        resultSet.addAllElements(lookupElements);
        resultSet.addLookupAdvertisement("匹配词数" + pairedWords.size());
    }

    @NotNull
    private Function<Word, LookupElementBuilder> buildLookupElementBuilder(FrequencyWordCacheComponent frequencyWordCacheComponent) {
        return word -> {
            boolean hasPhonetic = !StringUtil.isEmpty(word.phonetic);
            return LookupElementBuilder
                    .create(word.word.trim())
                    .withPresentableText(word.word.trim())
                    .withTypeText(hasPhonetic ? word.phonetic.trim() : null, hasPhonetic ? DictionaryIcons.PHONETIC : null, false)
                    .withTailText(word.translation == null ? null : word.translation.trim())
                    .withIcon(DictionaryIcons.DICTIONARY)
                    .withBoldness(false)

                    .bold()
                    .withInsertHandler((ctx, item) -> {

                        String insertText = item.getLookupString();
                        frequencyWordCacheComponent.add(insertText);

                        String[] insertWords = insertText.trim().split(" ");

                        String text = ctx.getDocument().getText(new TextRange(ctx.getStartOffset() - 1, ctx.getStartOffset()));
                        if (StringUtil.isEmpty(text) && insertWords.length == 0) {
                            return;
                        }

                        char symbol = text.charAt(0);
                        if (insertWords.length == 1) {
                            // helloWorld
                            if (Character.isLowerCase(symbol)) {
                                String w = StringUtil.capitalize(insertWords[0]);
                                ctx.getDocument().replaceString(ctx.getStartOffset(), ctx.getTailOffset(), w);
                            } else {
                                //   hello_world
                                ctx.getDocument().replaceString(ctx.getStartOffset(), ctx.getTailOffset(), insertWords[0]);
                            }
                        } else {
                            if (Character.isLowerCase(symbol)) {
                                StringBuilder builder = new StringBuilder();
                                for (String w : insertWords) {
                                    builder.append(StringUtil.capitalize(w));
                                }
                                ctx.getDocument().replaceString(ctx.getStartOffset(), ctx.getTailOffset(), builder.toString());
                            } else {
                                StringBuilder builder = new StringBuilder();
                                for (int i = 0; i < insertWords.length; i++) {
                                    String w = insertWords[i];
                                    if (i == 0) {
                                        builder.append(w.toLowerCase());
                                    } else {
                                        builder.append(StringUtil.capitalize(w));
                                    }
                                }
                                ctx.getDocument().replaceString(ctx.getStartOffset(), ctx.getTailOffset(), builder.toString());
                            }
                        }

                    });
        };
    }
}
