// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.github.ponking66.ccecdit.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.fields.IntegerField;
import com.intellij.util.ui.FormBuilder;
import org.github.ponking66.ccecdit.util.Constant;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class CodeCompletionSettingsComponent {

    private final JPanel panel;
    private final TextFieldWithBrowseButton ecdictSqlitePath = new TextFieldWithBrowseButton();
    private final JBCheckBox isUseCustomDictPath = new JBCheckBox(Constant.SETTING_ATTRIBUTE_SQLITE_HOME_PATH_NAME);
    private final IntegerField pairedWordCountTextField = new IntegerField("Maximum number of pairings", 10, 50);
    private final JBCheckBox isPriorityLatelyShow = new JBCheckBox(Constant.SETTING_ATTRIBUTE_IS_PRIORITY_LATELY_SHOW);

    public CodeCompletionSettingsComponent(CodeCompletionSettings settings) {


        this.isUseCustomDictPath.setSelected(settings.isCustom());
        this.isPriorityLatelyShow.setSelected(settings.isPriorityLatelyShow());
        this.pairedWordCountTextField.setValue(settings.getPairedWordCount());

        this.ecdictSqlitePath.setEditable(settings.isCustom());
        this.ecdictSqlitePath.addBrowseFolderListener(new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFileDescriptor()));

        this.panel = FormBuilder.createFormBuilder()
                .addLabeledComponent(isUseCustomDictPath, ecdictSqlitePath, 1, false)
                .addLabeledComponent(new JBLabel(Constant.SETTING_ATTRIBUTE_MAXIMUM_NUMBER_OF_PAIRINGS_NAME), pairedWordCountTextField, 1, false)
                .addComponent(isPriorityLatelyShow, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public JComponent getPreferredFocusedComponent() {
        return ecdictSqlitePath;
    }

    @NotNull
    public String getSqliteDictPath() {
        return ecdictSqlitePath.getText();
    }

    public void setSqliteDictPath(String text) {
        if (!StringUtil.isEmpty(text)) {
            ecdictSqlitePath.setText(text);
        }
    }

    public int getPairedWordCount() {
        return this.pairedWordCountTextField.getValue();
    }

    public void setPairedWordCount(int pairedWordCount) {
        this.pairedWordCountTextField.setValue(pairedWordCount);
    }

    public boolean isCustom() {
        return this.isUseCustomDictPath.isSelected();
    }

    public void setCustom(boolean custom) {
        this.isUseCustomDictPath.setSelected(custom);
    }

    public boolean isPriorityLatelyShow() {
        return this.isPriorityLatelyShow.isSelected();
    }

    public void setPriorityLatelyShow(boolean priorityLatelyShow) {
        this.isPriorityLatelyShow.setSelected(priorityLatelyShow);
    }


}
