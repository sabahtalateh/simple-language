package org.jetbrains.plugins.lang

import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import org.jetbrains.plugins.lang.psi.SimpleProperty


//class SimpleChooseByNameContributor2 : ChooseByNameContributor {
//    override fun getNames(project: Project?, includeNonProjectItems: Boolean): Array<String> {
//        val properties = SimpleUtil.findProperties(project)
//        val names: MutableList<String> = ArrayList(properties.size)
//        for (property in properties) {
//            if (property.key != null && property.key.isNotEmpty()) {
//                names.add(property.key)
//            }
//        }
//        return names.toTypedArray()
//    }
//
//
//    override fun getItemsByName(
//        name: String?,
//        pattern: String?,
//        project: Project?,
//        includeNonProjectItems: Boolean
//    ): Array<NavigationItem?>? {
//        // TODO: include non project items
//        val properties = SimpleUtil.findProperties(project!!, name!!)
//        return properties.toTypedArray()
//    }
//
////    override fun getItemsByName2(
////        name: String?,
////        pattern: String?,
////        project: Project?,
////        includeNonProjectItems: Boolean
////    ): Array<NavigationItem> {
//
//
//        // TODO: include non project items
//        val properties: List<SimpleProperty> = SimpleUtil.findProperties(project!!, name!!)
//        return properties.toTypedArray()
////        val result: MutableList<NavigationItem> = ArrayList<NavigationItem>()
////        val properties = SimpleUtil.findProperties(project!!, name!!)
////
////        for (prop in properties) {
////            result.add(prop)
////        }
////
////        return if (properties.isEmpty()) NavigationItem.EMPTY_NAVIGATION_ITEM_ARRAY else properties.toTypedArray()
////        return properties.toArray(NavigationItem[properties.size()]);
//    }
//}