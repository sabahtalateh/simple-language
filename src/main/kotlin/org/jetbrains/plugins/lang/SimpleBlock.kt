package org.jetbrains.plugins.lang

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock

class SimpleBlock(
    node: ASTNode,
    wrap: Wrap,
    alignment: Alignment,
    private val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, wrap, alignment) {
    override fun getSpacing(child1: Block?, child2: Block): Spacing? = spacingBuilder.getSpacing(this, child1, child2)

    override fun isLeaf(): Boolean = myNode.firstChildNode == null

    override fun buildChildren(): MutableList<Block> {
        val blocks: MutableList<Block> = ArrayList()
        var child = myNode.firstChildNode
        while (child != null) {
            if (child.elementType != TokenType.WHITE_SPACE) {
                val block: Block = SimpleBlock(
                    child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(),
                    spacingBuilder
                )
                blocks.add(block)
            }
            child = child.treeNext;
        }
        return blocks
    }

    override fun getIndent(): Indent {
        return Indent.getNoneIndent()
    }
}
