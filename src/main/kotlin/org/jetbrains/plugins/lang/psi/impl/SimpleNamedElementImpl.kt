package org.jetbrains.plugins.lang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import org.jetbrains.plugins.lang.psi.SimpleNamedElement

abstract class SimpleNamedElementImpl(node: ASTNode) : SimpleNamedElement, ASTWrapperPsiElement(node)
