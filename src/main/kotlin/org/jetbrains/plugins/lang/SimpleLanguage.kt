package org.jetbrains.plugins.lang

import com.intellij.lang.Language

open class SimpleLanguage : Language("Simple") {
    companion object {
        val INSTANCE = SimpleLanguage()
    }
}
