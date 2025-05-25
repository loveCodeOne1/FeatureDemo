pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
            //jetpack
        maven (uri("https://jitpack.io"))
    }
}

rootProject.name = "FeatureDemo"
include(":app")
 