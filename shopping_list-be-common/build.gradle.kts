plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val kotlinSerializationVersion: String by project

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
}
