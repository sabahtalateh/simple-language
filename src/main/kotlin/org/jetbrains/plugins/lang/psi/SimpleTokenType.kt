package org.jetbrains.plugins.lang.psi

import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.NotNull
import org.jetbrains.plugins.lang.SimpleLanguage

class SimpleTokenType(@NotNull @NonNls debugName: String?) :
    IElementType(debugName!!, SimpleLanguage.INSTANCE) {
    override fun toString(): String {
        return "SimpleTokenType." + super.toString()
    }
}
