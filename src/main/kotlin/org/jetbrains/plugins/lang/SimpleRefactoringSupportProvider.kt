package org.jetbrains.plugins.lang

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.lang.psi.SimpleProperty

class SimpleRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        return (element is SimpleProperty)
    }
}
