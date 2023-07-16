package org.github.ponking66.ccecdit.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author pony
 * @date 2023/7/14
 */
@State(
        name = "org.github.ponking66.ccecdit.settings.CodeCompletionSettings",
        storages = {@Storage("CCECDITSettingPlugin.xml")}
)
public class CodeCompletionSettings implements PersistentStateComponent<CodeCompletionSettings> {
    private int pairedWordCount = 20;

    private String dictionarySqliteFilePath;

    public static CodeCompletionSettings getInstance() {
        return ApplicationManager.getApplication().getService(CodeCompletionSettings.class);
    }

    @Override
    public @Nullable CodeCompletionSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull CodeCompletionSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public String getSqliteDictPath() {
        return dictionarySqliteFilePath;
    }

    public void setSqliteDictPath(String sqliteDictPath) {
        this.dictionarySqliteFilePath = sqliteDictPath;
    }


    public int getPairedWordCount() {
        return pairedWordCount;
    }

    public void setPairedWordCount(int pairedWordCount) {
        this.pairedWordCount = pairedWordCount;
    }
}
