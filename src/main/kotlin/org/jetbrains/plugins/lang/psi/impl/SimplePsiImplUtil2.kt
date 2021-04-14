package org.jetbrains.plugins.lang.psi.impl

import org.jetbrains.plugins.lang.psi.SimpleProperty
import org.jetbrains.plugins.lang.psi.SimpleTypes

class SimplePsiImplUtil2 {
    companion object {
        fun getKey(element: SimpleProperty): String? {
            val keyNode = element.node.findChildByType(SimpleTypes.KEY)
            return keyNode?.text?.replace("\\\\ ".toRegex(), " ")
        }

        fun getValue(element: SimpleProperty): String? {
            val valueNode = element.node.findChildByType(SimpleTypes.VALUE)
            return valueNode?.text
        }
    }
}