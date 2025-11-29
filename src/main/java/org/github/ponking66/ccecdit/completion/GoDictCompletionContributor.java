
package org.github.ponking66.ccecdit.completion;

import com.goide.psi.*;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class GoDictCompletionContributor extends CompletionContributor implements BaseCompletion, DumbAware {

    public GoDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
//        extend(CompletionType.BASIC, inPsiElement(), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoVarOrConstDefinition.class)), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoVarOrConstDeclaration.class)), provider);

        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoParamDefinition.class)), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoParameterDeclaration.class)), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoParameters.class)), provider);

        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoLabelDefinition.class)), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoTypeDeclaration.class)), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoTypeParameterDeclaration.class)), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoTypeParamDefinition.class)), provider);

        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoFieldDeclaration.class)), provider);
        extend(CompletionType.BASIC, inPsiElement().withParent(psiElement(GoFieldDefinition.class)), provider);

    }

    @Override
    public PsiElementPattern.Capture<PsiElement> inPsiElement() {
        return psiElement().inFile(psiElement(GoFile.class));
    }
}
