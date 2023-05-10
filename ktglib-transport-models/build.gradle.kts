plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val kotlinSerializationVersion: String by project
val kotestVersion: String by project

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

    testImplementation ("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation ("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation ("io.kotest:kotest-assertions-json:$kotestVersion")
    testImplementation ("io.kotest:kotest-property:$kotestVersion")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}