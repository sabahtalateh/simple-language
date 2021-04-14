package org.jetbrains.plugins.lang

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class SimpleColorSettingsPage : ColorSettingsPage {

    companion object {
        val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Key", SimpleSyntaxHighlighter.KEY),
            AttributesDescriptor("Separator", SimpleSyntaxHighlighter.SEPARATOR),
            AttributesDescriptor("Value", SimpleSyntaxHighlighter.VALUE),
            AttributesDescriptor("Bad Value", SimpleSyntaxHighlighter.BAD_CHARACTER)
        )
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return DESCRIPTORS
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String {
        return "Simple"
    }

    override fun getIcon(): Icon {
        return SimpleIcons.FILE
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return SimpleSyntaxHighlighter()
    }

    override fun getDemoText(): String {
        return "# You are reading the \".properties\" entry.\n" +
                "! The exclamation mark can also mark text as comments.\n" +
                "website = https://en.wikipedia.org/\n" +
                "language = English\n" +
                "# The backslash below tells the application to continue reading\n" +
                "# the value onto the next line.\n" +
                "message = Welcome to \\\n" +
                "          Wikipedia!\n" +
                "# Add spaces to the key\n" +
                "key\\ with\\ spaces = This is the value that could be looked up with the key \"key with spaces\".\n" +
                "# Unicode\n" +
                "tab : \\u0009"
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? {
        return null
    }
}