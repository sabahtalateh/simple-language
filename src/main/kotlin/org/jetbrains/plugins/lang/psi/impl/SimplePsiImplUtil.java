package org.jetbrains.plugins.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.lang.SimpleIcons;
import org.jetbrains.plugins.lang.psi.SimpleElementFactory;
import org.jetbrains.plugins.lang.psi.SimpleProperty;
import org.jetbrains.plugins.lang.psi.SimpleTypes;

import javax.swing.*;

public class SimplePsiImplUtil {
    public static String getKey(SimpleProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(SimpleProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(SimpleTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(SimpleProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(SimpleProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
        if (keyNode != null) {

            SimpleProperty property = SimpleElementFactory.Companion.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SimpleProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final SimpleProperty element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return element.getContainingFile().getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return SimpleIcons.INSTANCE.getFILE();
            }
        };
    }
}
