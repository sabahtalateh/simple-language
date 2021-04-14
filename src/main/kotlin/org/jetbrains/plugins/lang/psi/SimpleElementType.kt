package org.jetbrains.plugins.lang.psi

import com.intellij.psi.tree.IElementType
import org.jetbrains.plugins.lang.SimpleLanguage

class SimpleElementType(debugName: String) : IElementType(debugName, SimpleLanguage.INSTANCE)
