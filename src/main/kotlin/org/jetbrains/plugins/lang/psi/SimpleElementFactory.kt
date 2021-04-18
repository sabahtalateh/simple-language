package org.jetbrains.plugins.lang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import org.jetbrains.plugins.lang.SimpleFileType

class SimpleElementFactory {
    companion object {
        fun createProperty(project: Project, name: String): SimpleProperty {
            val file: SimpleFile = createFile(project, name)
            return file.firstChild as SimpleProperty
        }

        fun createFile(project: Project, text: String): SimpleFile {
            val name = "dummy.simple"
            return PsiFileFactory.getInstance(project)
                .createFileFromText(name, SimpleFileType.INSTANCE, text) as SimpleFile
        }
    }
}
