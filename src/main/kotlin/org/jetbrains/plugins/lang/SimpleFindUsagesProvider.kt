package org.jetbrains.plugins.lang

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import org.jetbrains.plugins.lang.psi.SimpleProperty
import org.jetbrains.plugins.lang.psi.SimpleTypes


class SimpleFindUsagesProvider : FindUsagesProvider {
    override fun getWordsScanner(): WordsScanner {
        return DefaultWordsScanner(
            SimpleLexerAdapter(),
            TokenSet.create(SimpleTypes.KEY),
            TokenSet.create(SimpleTypes.COMMENT),
            TokenSet.EMPTY
        )
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNamedElement;
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null;
    }

    override fun getType(element: PsiElement): String {
        return if (element is SimpleProperty) {
            "simple property"
        } else {
            ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return if (element is SimpleProperty) {
            element.key
        } else {
            ""
        }
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return if (element is SimpleProperty) {
            element.key + SimpleAnnotator.SIMPLE_SEPARATOR_STR + element.value
        } else {
            ""
        }
    }
}