package org.jetbrains.plugins.lang

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.util.PsiTreeUtil


class SimpleFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        // Initialize the group of folding regions that will expand/collapse together.
        val group = FoldingGroup.newGroup(SimpleAnnotator.SIMPLE_PREFIX_STR)
        // Initialize the list of folding regions
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        // Get a collection of the literal expressions in the document below root
        val literalExpressions = PsiTreeUtil.findChildrenOfType(root, PsiLiteralExpression::class.java)
        // Evaluate the collection
        for (literalExpression in literalExpressions) {
            val value = if (literalExpression.value is String) literalExpression.value as String else null
            if (value != null && value.startsWith(SimpleAnnotator.SIMPLE_PREFIX_STR + SimpleAnnotator.SIMPLE_SEPARATOR_STR)) {
                val project: Project = literalExpression.project
                val key = value.substring(
                    SimpleAnnotator.SIMPLE_PREFIX_STR.length + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length
                )
                // Get a list of all properties for a given key in the project
                val properties = SimpleUtil.findProperties(project, key)
                if (properties.size == 1) {
                    // Add a folding descriptor for the literal expression at this node.
                    // Add a folding descriptor for the literal expression at this node.
                    descriptors.add(
                        FoldingDescriptor(
                            literalExpression.node,
                            TextRange(
                                literalExpression.textRange.startOffset + 1,
                                literalExpression.textRange.endOffset - 1
                            ),
                            group
                        )
                    )
                }
            }
        }

        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String {
        val retTxt = "..."
        if (node.psi is PsiLiteralExpression) {
            val nodeElement = node.psi as PsiLiteralExpression
            val key = (nodeElement.value as String).substring(
                SimpleAnnotator.SIMPLE_PREFIX_STR.length + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length
            )
            val properties = SimpleUtil.findProperties(nodeElement.project, key)
            val place = properties[0].value
            // IMPORTANT: keys can come with no values, so a test for null is needed
            // IMPORTANT: Convert embedded \n to backslash n, so that the string will look
            // like it has LF embedded in it and embedded " to escaped "
            return place?.replace("\n", "\\n")?.replace("\"", "\\\\\"") ?: retTxt
        }

        return retTxt
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return true
    }
}