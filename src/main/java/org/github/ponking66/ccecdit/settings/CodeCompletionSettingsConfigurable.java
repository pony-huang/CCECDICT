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

    private final CodeCompletionSettings settings = CodeCompletionSettings.getInstance();

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
        settingsComponent = new CodeCompletionSettingsComponent(settings);
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        boolean modified = false;
        // path
        String currentSqliteDictPath = settingsComponent.getSqliteDictPath();
        if (currentSqliteDictPath.length() > 0) {
            modified = !currentSqliteDictPath.equals(settings.getSqliteDictPath());
            settings.setSqliteDictPath(currentSqliteDictPath);
        }

        // paired word count
        int pairedWordCount = settingsComponent.getPairedWordCount();
        modified |= pairedWordCount != settings.getPairedWordCount();

        // isCustom button
        modified = isModified(modified, settingsComponent.isCustom(), settings.isCustom());
        // isPriority button
        modified = isModified(modified, settingsComponent.isPriorityLatelyShow(), settings.isPriorityLatelyShow());

        return modified;
    }


    private boolean isModified(boolean modified, boolean current, boolean old) {
        modified |= current != old;
        return modified;
    }

    @Override
    public void apply() {
        CodeCompletionSettings settings = CodeCompletionSettings.getInstance();
        settings.setSqliteDictPath(settingsComponent.getSqliteDictPath());
        settings.setPairedWordCount(settingsComponent.getPairedWordCount());
        settings.setCustom(settingsComponent.isCustom());
        settings.setPriorityLatelyShow(settingsComponent.isPriorityLatelyShow());
    }

    @Override
    public void reset() {
        settingsComponent.setSqliteDictPath(settings.getSqliteDictPath());
        settingsComponent.setPairedWordCount(settings.getPairedWordCount());
        settingsComponent.setCustom(settings.isCustom());
        settingsComponent.setPriorityLatelyShow(settings.isPriorityLatelyShow());
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }

}
