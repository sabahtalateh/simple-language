package com.github.sabahtalateh.simplelanguage.services

import com.github.sabahtalateh.simplelanguage.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
