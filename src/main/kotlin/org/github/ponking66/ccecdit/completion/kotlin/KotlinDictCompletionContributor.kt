package org.github.ponking66.ccecdit.completion.kotlin

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import org.github.ponking66.ccecdit.completion.DictCompletionProvider
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNameReferenceExpression

class KotlinDictCompletionContributor : CompletionContributor(), DumbAware {

    init {
        val provider = object : DictCompletionProvider() {}
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(KtFile::class.java).withParent(KtNameReferenceExpression::class.java),
            provider
        )
        extend(
            CompletionType.SMART,
            PlatformPatterns.psiElement(KtFile::class.java).withParent(KtNameReferenceExpression::class.java),
            provider
        )

    }

}