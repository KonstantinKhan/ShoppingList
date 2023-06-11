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

suspend fun HttpClient.sendRecipientNotification(context: BeContext): Response {
    val response = this.post("sendMessage") {
        contentType(ContentType.Application.Json)
        setBody(message {
            chatId = context.recipient.userId.toLong()
            text = "@${context.shoppingList.user.userName} поделился с вами списком покупок"
        })
    }
    return jsonHelper().decodeFromString(response.bodyAsText())
}