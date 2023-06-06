plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val ktorVersion: String by project

dependencies {
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
}
