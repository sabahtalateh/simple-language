package org.jetbrains.plugins.lang

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl


class SimpleLineMarkerProvider : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>
    ) {
        // This must be an element with a literal expression as a parent
        if (element !is PsiJavaTokenImpl || element.getParent() !is PsiLiteralExpression) {
            return;
        }

        // The literal expression must start with the Simple language literal expression
        val literalExpression = element.getParent() as PsiLiteralExpression
        val value = if (literalExpression.value is String) literalExpression.value as String? else null
        if (value == null ||
            !value.startsWith(SimpleAnnotator.SIMPLE_PREFIX_STR + SimpleAnnotator.SIMPLE_SEPARATOR_STR)
        ) {
            return
        }

        // Get the Simple language property usage
        val project: Project = element.getProject()
        val possibleProperties = value.substring(
            SimpleAnnotator.SIMPLE_PREFIX_STR.length + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length
        )
        val properties = SimpleUtil.findProperties(project, possibleProperties)
        if (properties.isNotEmpty()) {
            // Add the property to a collection of line marker info
            val builder: NavigationGutterIconBuilder<PsiElement> = NavigationGutterIconBuilder.create(SimpleIcons.FILE)
                .setTargets(properties)
                .setTooltipText("Navigate to Simple language property")
            result.add(builder.createLineMarkerInfo(element))
        }
    }
}