plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("io.ktor.plugin") version "2.3.0"
}

val ktorVersion: String by project
val logbackVersion: String by project

dependencies {
    implementation("io.ktor:ktor-server-core:2.2.4")
    implementation("io.ktor:ktor-server-netty:2.2.4")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation(project(":shopping_list-be-common"))
    implementation(project(":ktglib-transport-models"))
    implementation(project(":ktglib-types"))
    implementation(project(":shopping_list-be-lib-telegram-api"))
    implementation(project(":shopping_list-be-repo_in_memory"))
    implementation(project(":shopping_list-be-shopping_list-service"))
    implementation(project(":shopping_list-be-shopping_list-logic"))
    implementation(project(":shopping_list-be-sql"))
    implementation(project(":shopping_list-sender_service"))

}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}