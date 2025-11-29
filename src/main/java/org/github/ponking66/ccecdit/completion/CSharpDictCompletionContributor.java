
package org.github.ponking66.ccecdit.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class CSharpDictCompletionContributor extends CompletionContributor implements BaseCompletion, DumbAware {

    public CSharpDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        // C# identifiers (variables, methods, fields, parameters, classes, etc.)
        // Using generic pattern as Rider uses ReSharper PSI structure
        extend(CompletionType.BASIC, inPsiElement(), provider);
    }

    @Override
    public PsiElementPattern.Capture<PsiElement> inPsiElement() {
        return psiElement(PsiElement.class);
    }
}
