// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.plugins.lang;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.lang.psi.SimpleProperty;

import java.util.ArrayList;
import java.util.List;

public class SimpleChooseByNameContributor implements ChooseByNameContributor {

    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<SimpleProperty> properties = SimpleUtil.Companion.findProperties(project);
        List<String> names = new ArrayList<>(properties.size());
        for (SimpleProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                names.add(property.getKey());
            }
        }
        return names.toArray(new String[names.size()]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // TODO: include non project items
        List<SimpleProperty> properties = SimpleUtil.Companion.findProperties(project, name);
        return properties.toArray(new NavigationItem[properties.size()]);
    }

}
