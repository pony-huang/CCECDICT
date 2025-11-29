
package org.github.ponking66.ccecdit.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.json.psi.*;
import com.intellij.openapi.project.DumbAware;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class JsonDictCompletionContributor extends CompletionContributor implements DumbAware {

    public JsonDictCompletionContributor() {
        CompletionProvider<CompletionParameters> provider = new DictCompletionProvider();
        // JSON property names (keys)
        extend(CompletionType.BASIC, psiElement().withParent(JsonProperty.class), provider);
        // JSON string literals (values)
        extend(CompletionType.BASIC, psiElement().withParent(JsonStringLiteral.class), provider);
    }
}
