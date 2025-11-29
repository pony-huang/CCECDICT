
package org.github.ponking66.ccecdit.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import org.rust.lang.core.psi.*;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class RustDictCompletionContributor extends CompletionContributor implements DumbAware {

    public RustDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        // Variable bindings (let bindings)
        extend(CompletionType.BASIC, parentPsiElement().withParent(RsPatBinding.class), provider);
        // Function definitions
        extend(CompletionType.BASIC, parentPsiElement().withParent(RsFunction.class), provider);
        // Struct definitions
        extend(CompletionType.BASIC, parentPsiElement().withParent(RsStructItem.class), provider);
        // Struct field declarations
        extend(CompletionType.BASIC, parentPsiElement().withParent(RsNamedFieldDecl.class), provider);
    }

    private static PsiElementPattern.Capture<PsiElement> parentPsiElement() {
        return psiElement().inFile(psiElement(RsFile.class));
    }
}
