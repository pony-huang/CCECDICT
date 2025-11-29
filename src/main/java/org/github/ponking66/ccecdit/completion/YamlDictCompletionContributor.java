
package org.github.ponking66.ccecdit.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import org.jetbrains.yaml.psi.YAMLFile;
import org.jetbrains.yaml.psi.YAMLKeyValue;
import org.jetbrains.yaml.psi.YAMLScalar;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class YamlDictCompletionContributor extends CompletionContributor implements BaseCompletion, DumbAware {

    public YamlDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(YAMLKeyValue.class)), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(YAMLScalar.class)), provider);
    }

    @Override
    public PsiElementPattern.Capture<?> inPsiElement() {
        return psiElement().inFile(psiElement(YAMLFile.class));
    }
}
