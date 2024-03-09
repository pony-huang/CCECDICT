
package org.github.ponking66.ccecdit.completion.yaml;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import org.github.ponking66.ccecdit.completion.DictCompletionProvider;
import org.jetbrains.yaml.psi.YAMLFile;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class YamlDictCompletionContributor extends CompletionContributor implements DumbAware {

    public YamlDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        extend(CompletionType.BASIC, inPyFile(), provider);
    }

    private static PsiElementPattern.Capture<PsiElement> inPyFile() {
        return psiElement().inFile(psiElement(YAMLFile.class));
    }
}
