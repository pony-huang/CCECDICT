
package org.github.ponking66.ccecdit.completion.java;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.*;
import org.github.ponking66.ccecdit.completion.DictCompletionProvider;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class JavaDictCompletionContributor extends CompletionContributor implements DumbAware {

    public JavaDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiLocalVariable.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiMethod.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiField.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiParameter.class), provider);
    }
}
