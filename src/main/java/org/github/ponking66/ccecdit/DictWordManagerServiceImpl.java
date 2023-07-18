package org.github.ponking66.ccecdit;

import com.intellij.openapi.application.ApplicationManager;
import org.github.ponking66.ccecdit.settings.CodeCompletionSettings;

import java.util.List;

/**
 * @author pony
 * @date 2023/7/17
 */
public class DictWordManagerServiceImpl implements DictWordManagerService {

    private final DictWordSqliteManagerService customManagerService = new CustomDictWordSqliteManagerServiceImpl();

    private final DictWordSqliteManagerService defaultManagerService = new DefaultDictWordSqliteManagerServiceImpl();

    @Override
    public List<Word> searchWords(String prefix) {
        CodeCompletionSettings settings = ApplicationManager.getApplication().getService(CodeCompletionSettings.class);
        if (settings.isCustom()) {
            return customManagerService.searchWords(prefix);
        } else {
            return defaultManagerService.searchWords(prefix);
        }
    }

    @Override
    public List<Word> searchWords(String prefix, int limit) {
        CodeCompletionSettings settings = ApplicationManager.getApplication().getService(CodeCompletionSettings.class);
        if (settings.isCustom()) {
            return customManagerService.searchWords(prefix, limit);
        } else {
            return defaultManagerService.searchWords(prefix, limit);
        }
    }

    @Override
    public List<Word> searchWords(String prefix, List<String> words, int limit) {
        CodeCompletionSettings settings = ApplicationManager.getApplication().getService(CodeCompletionSettings.class);
        if (settings.isCustom()) {
            return customManagerService.searchWords(prefix, words, limit);
        } else {
            return defaultManagerService.searchWords(prefix, words, limit);
        }
    }

}
