package org.github.ponking66.ccecdit.completion;

import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.codeInsight.completion.impl.CamelHumpMatcher;
import com.intellij.psi.codeStyle.MinusculeMatcher;
import com.intellij.psi.codeStyle.NameUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author pony
 * @date 2023/7/13
 */
public class WordPrefixMatcher extends PrefixMatcher {

    private final MinusculeMatcher matcher;

    public WordPrefixMatcher(String prefix) {
        super(prefix);
        matcher = NameUtil.buildMatcher(CamelHumpMatcher.applyMiddleMatching(getPrefix())).typoTolerant().build();
    }

    @Override
    public boolean prefixMatches(@NotNull String name) {
        return matcher.isStartMatch(name);
    }

    @Override
    public int matchingDegree(String string) {
        return string.startsWith(getPrefix()) ? 1 : 0;
    }

    @Override
    public @NotNull
    PrefixMatcher cloneWithPrefix(@NotNull String prefix) {
        return new WordPrefixMatcher(prefix);
    }

}
