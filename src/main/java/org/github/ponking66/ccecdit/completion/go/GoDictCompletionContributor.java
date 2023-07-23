
package org.github.ponking66.ccecdit.completion.go;

import com.goide.psi.GoFile;
import com.goide.psi.GoFunctionOrMethodDeclaration;
import com.goide.psi.GoParamDefinition;
import com.goide.psi.GoVarOrConstDefinition;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import org.github.ponking66.ccecdit.completion.DictCompletionProvider;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class GoDictCompletionContributor extends CompletionContributor implements DumbAware {

    public GoDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
//        extend(CompletionType.BASIC, inGoFile(), provider);
        extend(CompletionType.BASIC, inGoFile().withParent(psiElement(GoVarOrConstDefinition.class)), provider);
        extend(CompletionType.BASIC, inGoFile().withParent(psiElement(GoFunctionOrMethodDeclaration.class)), provider);
        extend(CompletionType.BASIC, inGoFile().withParent(psiElement(GoParamDefinition.class)), provider);
    }

    private static PsiElementPattern.Capture<PsiElement> inGoFile() {
        return psiElement().inFile(psiElement(GoFile.class));
    }
}
