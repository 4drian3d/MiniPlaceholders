pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "miniplaceholders-parent"

val modules = listOf("connect", "api", "common", "paper", "velocity")

modules.forEach {
    include("miniplaceholders-$it")
    project(":miniplaceholders-$it").projectDir = file(it)
}

