package org.github.ponking66.ccecdit.completion.kotlin

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import org.github.ponking66.ccecdit.completion.DictCompletionProvider
import org.jetbrains.kotlin.psi.KtFile

class KotlinDictCompletionContributor : CompletionContributor(), DumbAware {

    init {
        val provider = object : DictCompletionProvider() {}
        extend(
            CompletionType.BASIC,
            inKtFile(),
            provider
        )
        extend(
            CompletionType.SMART,
            inKtFile(),
            provider
        )

    }

    private fun inKtFile(): PsiElementPattern.Capture<PsiElement?> {
        return PlatformPatterns.psiElement().inFile(
            PlatformPatterns.psiElement(
                KtFile::class.java
            )
        )
    }

}