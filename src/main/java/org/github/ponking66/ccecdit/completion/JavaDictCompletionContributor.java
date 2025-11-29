
package org.github.ponking66.ccecdit.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.*;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class JavaDictCompletionContributor extends CompletionContributor implements BaseCompletion, DumbAware {

    public JavaDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiLocalVariable.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiMethod.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiField.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiParameter.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiClass.class), provider);
    }

    @Override
    public PsiElementPattern.Capture<PsiIdentifier> inPsiElement() {
        return psiElement(PsiIdentifier.class);
    }
}
