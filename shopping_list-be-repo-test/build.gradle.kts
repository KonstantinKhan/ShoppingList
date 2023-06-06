plugins {
    kotlin("jvm")
}

val kotestVersion: String by project

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC")

    implementation ("io.kotest:kotest-runner-junit5:$kotestVersion")
    implementation ("io.kotest:kotest-assertions-core:$kotestVersion")
    implementation ("io.kotest:kotest-property:$kotestVersion")

    implementation(project(":shopping_list-be-common"))
}