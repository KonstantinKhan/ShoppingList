package com.shopping_list.app.ktor.controllers

import com.shopping_list.app.ktor.AppKtorConfig
import com.shopping_list.app.ktor.helpers.routingUpdate
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.ktglib.transport.models.update.Update

suspend fun ApplicationCall.sendRequestToBot(config: AppKtorConfig) {
    val updateRequest = receive<Update>()
    routingUpdate(updateRequest, config)
    respondText("Sent request to bot")
}