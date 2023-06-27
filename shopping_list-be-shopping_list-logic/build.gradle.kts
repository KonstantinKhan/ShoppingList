plugins {
    kotlin("jvm")
}

val kotestVersion: String by project

dependencies {
    implementation(project(":shopping_list-be-common"))
    implementation(project(":shopping_list-cor"))
    implementation(project(":shopping_list-be-sql"))

    testImplementation ("io.kotest:kotest-runner-junit5:$kotestVersion")
    implementation ("io.kotest:kotest-assertions-core:$kotestVersion")
    implementation ("io.kotest:kotest-property:$kotestVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    testImplementation("org.testcontainers:postgresql:1.18.0")
    testImplementation(project(":shopping_list-be-repo-test"))
}
