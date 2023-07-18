package org.github.ponking66.ccecdit;

import com.intellij.openapi.application.ApplicationManager;
import org.github.ponking66.ccecdit.settings.CodeCompletionSettings;
import org.github.ponking66.ccecdit.util.SqliteUtil;

/**
 * @author pony
 * @date 2023/7/14
 */
public class CustomDictWordSqliteManagerServiceImpl extends DefaultDictWordSqliteManagerServiceImpl {

    protected String connectionUrl() {
        CodeCompletionSettings settings = ApplicationManager.getApplication().getService(CodeCompletionSettings.class);
        return SqliteUtil.SQLITE + settings.getSqliteDictPath();
    }
}
