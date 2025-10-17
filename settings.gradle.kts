pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EchoLog"
include(":app")
include(":domain")
include(":core:common")
include(":core:ui")
include(":data")
include(":feature:map")
include(":feature:echo_creation")
include(":feature:echo_detail")
include(":feature:journals")

includeBuild("build-logic")
