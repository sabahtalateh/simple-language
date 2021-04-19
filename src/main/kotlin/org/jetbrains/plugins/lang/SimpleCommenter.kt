package org.jetbrains.plugins.lang

import com.intellij.lang.Commenter

class SimpleCommenter : Commenter {
    override fun getLineCommentPrefix(): String = "#"

    override fun getBlockCommentPrefix(): String = ""

    override fun getBlockCommentSuffix(): String? = null

    override fun getCommentedBlockCommentPrefix(): String? = null

    override fun getCommentedBlockCommentSuffix(): String? = null
}