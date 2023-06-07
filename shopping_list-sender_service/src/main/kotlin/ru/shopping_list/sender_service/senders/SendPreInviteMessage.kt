package ru.shopping_list.sender_service.senders

import com.shopping_list.lib.telegram.api.dsl.message
import com.shopping_list.common.models.UserId
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import com.shopping_list.response.Response
import ru.shopping_list.sender_service.jsonHelper

suspend fun HttpClient.sendPreInviteMessage(chatId: UserId): Response {
    val response = this.post("sendMessage") {
        contentType(ContentType.Application.Json)
        setBody(message {
            this.chatId = chatId.toLong()
            text = "Выбранный пользователь ещё не пользуется ботом. \n" +
                    "Перешлите ему пригласительное сообщение"
        })
    }

    return jsonHelper().decodeFromString(response.bodyAsText())
}