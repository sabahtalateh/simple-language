package org.jetbrains.plugins.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import org.jetbrains.annotations.NotNull
import javax.swing.Icon

class SimpleFileType : LanguageFileType(SimpleLanguage.INSTANCE) {
    companion object {
        val INSTANCE = SimpleFileType()
    }

    override fun getName(): String {
        return "Simple File"
    }

    override fun getDescription(): String {
        return "Simple language file"
    }

    override fun getDefaultExtension(): String {
        return "simple"
    }

    @NotNull
    override fun getIcon(): Icon {
        return SimpleIcons.FILE
    }
}