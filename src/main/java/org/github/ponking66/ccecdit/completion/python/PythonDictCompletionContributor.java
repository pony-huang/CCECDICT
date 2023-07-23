
package org.github.ponking66.ccecdit.completion.python;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyFile;
import org.github.ponking66.ccecdit.completion.DictCompletionProvider;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class PythonDictCompletionContributor extends CompletionContributor implements DumbAware {

    public PythonDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        extend(CompletionType.BASIC, inPyFile(), provider);
    }

    private static PsiElementPattern.Capture<PsiElement> inPyFile() {
        return psiElement().inFile(psiElement(PyFile.class));
    }
}
