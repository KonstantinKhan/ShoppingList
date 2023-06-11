plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val ktorVersion: String by project

dependencies {
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation(project(":shopping_list-be-common"))
    implementation(project(":shopping_list-be-lib-telegram-api"))
    implementation(project(":ktglib-transport-models"))
    implementation(project(":ktglib-types"))
    implementation(project(":shopping_list-be-mapping"))
}