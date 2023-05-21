plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":shopping_list-be-common"))
    implementation(project(":ktglib-transport-models"))
    implementation(project(":shopping_list-be-lib-telegram-api"))
    implementation(project(":shopping_list-be-shopping_list-logic"))
    implementation(project(":shopping_list-be-mapping"))
}