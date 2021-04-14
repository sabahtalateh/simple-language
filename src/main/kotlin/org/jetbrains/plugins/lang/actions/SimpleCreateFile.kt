package org.jetbrains.plugins.lang.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import org.jetbrains.plugins.lang.SimpleIcons

class SimpleCreateFile : CreateFileFromTemplateAction("", "", SimpleIcons.FILE) {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle(NAME)
            .addKind("Empty file", SimpleIcons.FILE, "Simple File")
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String = NAME

    companion object {
        private const val NAME = "Simple File"
    }
}