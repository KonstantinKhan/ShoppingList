plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val ktorVersion: String by project

dependencies {
    api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation(project(":ktglib-transport-models"))
}
