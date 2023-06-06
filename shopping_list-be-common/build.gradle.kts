plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

val ktorVersion: String by project
val kotlinSerializationVersion: String by project

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
}
