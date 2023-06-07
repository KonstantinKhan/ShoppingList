rootProject.name = "shopping_list_bot"

pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
    }
}

include("shopping_list_bot-be-ktor-app")
include("shopping_list-be-lib-telegram-core")
include("shopping_list-be-common")
include("shopping_list-be-lib-telegram-api")
include("shopping_list-be-mapping")
include("shopping_list-be-repo_in_memory")
include("shopping_list-be-shopping_list-service")
include("shopping_list-be-shopping_list-logic")
include("shopping_list-cor")
include("shopping_list-be-sql")
include("shopping_list-be-repo-test")
include("ktglib-transport-models")
include("shopping_list-sender_service")
include("ktglib-types")
