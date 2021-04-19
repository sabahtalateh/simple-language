package org.jetbrains.plugins.lang

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleSettings
import org.jetbrains.plugins.lang.psi.SimpleTypes


class SimpleFormattingModelBuilder : FormattingModelBuilder {

    private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
        return SpacingBuilder(settings, SimpleLanguage.INSTANCE)
            .around(SimpleTypes.SEPARATOR)
            .spaceIf(settings.getCommonSettings(SimpleLanguage.INSTANCE.id).SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .before(SimpleTypes.PROPERTY)
            .none()
    }

    override fun createModel(element: PsiElement?, settings: CodeStyleSettings): FormattingModel {
        return FormattingModelProvider
            .createFormattingModelForPsiFile(
                element!!.containingFile,
                SimpleBlock(
                    element.node,
                    Wrap.createWrap(WrapType.NONE, false),
                    Alignment.createAlignment(),
                    createSpaceBuilder(settings)
                ),
                settings
            )
    }

    override fun getRangeAffectingIndent(file: PsiFile?, offset: Int, elementAtOffset: ASTNode?): TextRange? = null
}
