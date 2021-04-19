package org.jetbrains.plugins.lang

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.plugins.lang.psi.SimpleFile
import org.jetbrains.plugins.lang.psi.SimpleProperty
import org.jetbrains.plugins.lang.psi.impl.SimplePropertyImpl

class SimpleStructureViewElement(private val element: NavigatablePsiElement) : StructureViewTreeElement,
    SortableTreeElement {

    override fun getPresentation(): ItemPresentation {
        return element.presentation ?: PresentationData()
    }

    override fun getChildren(): Array<TreeElement> {
        if (element is SimpleFile) {
            val properties = PsiTreeUtil.getChildrenOfTypeAsList(
                element,
                SimpleProperty::class.java
            )
            val treeElements: MutableList<TreeElement> = ArrayList(1);
            for (property in properties) {
                treeElements.add(SimpleStructureViewElement(property as SimplePropertyImpl))
            }
            return treeElements.toTypedArray()
        }

        return emptyArray()
    }

    override fun navigate(requestFocus: Boolean) {
        element.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return element.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return element.canNavigateToSource()
    }

    override fun getValue(): Any {
        return element
    }

    override fun getAlphaSortKey(): String {
        val name = element.name
        return name ?: ""
    }
}
