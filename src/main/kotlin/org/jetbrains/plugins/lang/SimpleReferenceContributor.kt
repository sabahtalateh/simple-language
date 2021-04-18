package org.jetbrains.plugins.lang

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import org.jetbrains.plugins.lang.SimpleAnnotator.Companion.SIMPLE_PREFIX_STR
import org.jetbrains.plugins.lang.SimpleAnnotator.Companion.SIMPLE_SEPARATOR_STR

class SimpleReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression::class.java),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    val literalExpression = element as PsiLiteralExpression
                    val value = if (literalExpression.value is String) literalExpression.value as String? else null
                    if (value != null && value.startsWith(SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR)) {
                        val property = TextRange(
                            SIMPLE_PREFIX_STR.length + SIMPLE_SEPARATOR_STR.length + 1,
                            value.length + 1
                        )
                        return arrayOf(SimpleReference(element, property))
                    }
                    return PsiReference.EMPTY_ARRAY
                }
            })
    }
}
