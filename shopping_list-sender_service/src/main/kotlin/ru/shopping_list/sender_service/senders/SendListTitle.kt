package ru.shopping_list.sender_service.senders

import com.shopping_list.common.context.BeContext
import com.shopping_list.lib.telegram.api.dsl.message
import com.shopping_list.response.Response
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import ru.shopping_list.sender_service.jsonHelper

suspend fun HttpClient.sendListTitle(context: BeContext): Response {
    val response = this.post("sendMessage") {
        contentType(ContentType.Application.Json)
        setBody(message {
            chatId = context.shoppingList.user.userId.toLong()
            text = "Текущее название списка: ${context.shoppingList.title}\n" +
                    "Укажите новое название"
        })
    }
    return jsonHelper().decodeFromString(response.bodyAsText())
}