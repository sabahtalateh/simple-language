package org.jetbrains.plugins.lang

import com.intellij.lang.ASTNode
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.plugins.lang.psi.SimpleElementFactory
import org.jetbrains.plugins.lang.psi.SimpleFile
import org.jetbrains.plugins.lang.psi.SimpleProperty
import org.jetbrains.plugins.lang.psi.SimpleTypes
import org.jetbrains.plugins.lang.psi.impl.SimplePsiImplUtil.getKey
import java.util.*


class SimpleUtil {
    companion object {
        fun findProperties(project: Project, key: String): List<SimpleProperty> {
            val result: MutableList<SimpleProperty> = ArrayList()
            val virtualFiles = FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(project))
            for (virtualFile in virtualFiles) {
                val simpleFile = PsiManager.getInstance(project).findFile(virtualFile!!) as SimpleFile?
                if (simpleFile != null) {
                    val properties = PsiTreeUtil.getChildrenOfType(
                        simpleFile,
                        SimpleProperty::class.java
                    )
                    if (properties != null) {
                        for (property in properties) {
                            if (key == property.key) {
                                result.add(property)
                            }
                        }
                    }
                }
            }
            return result
        }

        fun findProperties(project: Project?): List<SimpleProperty> {
            val result: MutableList<SimpleProperty> = ArrayList()
            val virtualFiles = FileTypeIndex.getFiles(
                SimpleFileType.INSTANCE, GlobalSearchScope.allScope(
                    project!!
                )
            )
            for (virtualFile in virtualFiles) {
                val simpleFile = PsiManager.getInstance(project).findFile(virtualFile!!) as SimpleFile?
                if (simpleFile != null) {
                    val properties = PsiTreeUtil.getChildrenOfType(
                        simpleFile,
                        SimpleProperty::class.java
                    )
                    if (properties != null) {
                        Collections.addAll(result, *properties)
                    }
                }
            }
            return result
        }
    }
}