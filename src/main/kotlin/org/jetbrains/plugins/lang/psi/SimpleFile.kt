package org.jetbrains.plugins.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.jetbrains.plugins.lang.SimpleFileType
import org.jetbrains.plugins.lang.SimpleLanguage

class SimpleFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SimpleLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return SimpleFileType.INSTANCE
    }

    override fun toString(): String {
        return "Simple File";
    }
}