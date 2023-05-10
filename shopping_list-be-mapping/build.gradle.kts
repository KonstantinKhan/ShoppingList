plugins {
    kotlin("jvm")
}

val kotestVersion: String by project

dependencies {
    implementation(project(":shopping_list-be-common"))
    implementation(project(":ktglib-transport-models"))
    implementation(project(":shopping_list-be-lib-telegram-api"))

    testImplementation ("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation ("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation ("io.kotest:kotest-property:$kotestVersion")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
