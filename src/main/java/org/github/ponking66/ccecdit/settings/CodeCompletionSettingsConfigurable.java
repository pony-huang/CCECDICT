// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.github.ponking66.ccecdit.settings;

import com.intellij.openapi.options.Configurable;
import org.github.ponking66.ccecdit.util.Constant;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
 */
public class CodeCompletionSettingsConfigurable implements Configurable {

    private CodeCompletionSettingsComponent settingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return Constant.PROJECT_NAME;
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = new CodeCompletionSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        CodeCompletionSettings settings = CodeCompletionSettings.getInstance();
        // path
        String currentSqliteDictPath = settingsComponent.getSqliteDictPath();
        boolean modified = !currentSqliteDictPath.equals(settings.getSqliteDictPath());
        // paired word count
        int pairedWordCount = settingsComponent.getPairedWordCount();
        modified |= pairedWordCount != settings.getPairedWordCount();
        return modified;
    }

    @Override
    public void apply() {
        CodeCompletionSettings settings = CodeCompletionSettings.getInstance();
        settings.setSqliteDictPath(settingsComponent.getSqliteDictPath());
        settings.setPairedWordCount(settingsComponent.getPairedWordCount());
    }

    @Override
    public void reset() {
        CodeCompletionSettings settings = CodeCompletionSettings.getInstance();
        settingsComponent.setSqliteDictPath(settings.getSqliteDictPath());
        settingsComponent.setPairedWordCount(settings.getPairedWordCount());
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }

}
