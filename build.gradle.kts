plugins {
    kotlin("jvm") apply false
    kotlin("plugin.serialization") apply false
}

group = "com.khan366kos"
version = "0.0.5"

subprojects {
    group = rootProject.group
    version = rootProject.version
    repositories {
        mavenCentral()
    }
}
