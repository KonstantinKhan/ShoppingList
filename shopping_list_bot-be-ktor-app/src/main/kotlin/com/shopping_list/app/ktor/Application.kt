package com.shopping_list.app.ktor

import com.shopping_list.app.ktor.controllers.sendRequestToBot
import com.shopping_list.app.ktor.plugins.installPluginContentNegotiation
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(
    config: AppKtorConfig = AppKtorConfig(environment)
) {
    installPluginContentNegotiation()

    routing {
        get("/") {
            call.respond("Hello, ktor!")
        }
        post("/") {
            call.sendRequestToBot(config)
        }
    }
}