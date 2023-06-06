package ru.shopping_list.sender_service.senders

import com.shopping_list.common.context.BeContext
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import ru.ktglib.transport.models.request.ForwardMessageModel
import com.shopping_list.response.Response
import ru.shopping_list.sender_service.jsonHelper

suspend fun HttpClient.forwardMessage(context: BeContext): Response {
    val response = this.post("forwardMessage") {
        contentType(ContentType.Application.Json)
        setBody(
            ForwardMessageModel(
                chatId = context.recipient.userId.toInt(),
                fromChatId = context.shoppingList.user.userId.toInt(),
                messageId = context.messageId.toInt()
            )
        )
    }
    return jsonHelper().decodeFromString(response.bodyAsText())
}