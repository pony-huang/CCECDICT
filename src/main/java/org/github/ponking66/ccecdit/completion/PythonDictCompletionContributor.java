
package org.github.ponking66.ccecdit.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import com.jetbrains.python.psi.*;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class PythonDictCompletionContributor extends CompletionContributor implements BaseCompletion, DumbAware {

    public PythonDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        // Function parameters
        extend(CompletionType.BASIC, inPsiElement().withParent(PyNamedParameter.class), provider);
        // Variable assignments (local variables)
        extend(CompletionType.BASIC, inPsiElement().withParent(PyTargetExpression.class), provider);
        // Function definitions
        extend(CompletionType.BASIC, inPsiElement().withParent(PyFunction.class), provider);
        // Class definitions
        extend(CompletionType.BASIC, inPsiElement().withParent(PyClass.class), provider);
    }

    @Override
    public PsiElementPattern.Capture<?> inPsiElement() {
        return psiElement();
    }
}
