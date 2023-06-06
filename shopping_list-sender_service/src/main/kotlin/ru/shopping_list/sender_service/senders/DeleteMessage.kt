package ru.shopping_list.sender_service.senders

import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.UserId
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

suspend fun HttpClient.deleteMessage(chatId: UserId, messageId: MessageId) {
    val response = this.post("deleteMessage") {
        contentType(ContentType.Application.Json)
//        setBody(DeleteMessageRequest(chatId = chatId.toInt(), messageId = messageId.toInt()))
    }
}