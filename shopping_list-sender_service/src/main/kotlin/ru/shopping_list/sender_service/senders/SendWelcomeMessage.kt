package ru.shopping_list.sender_service.senders

import com.shopping_list.lib.telegram.api.dsl.button
import com.shopping_list.lib.telegram.api.dsl.message
import com.shopping_list.lib.telegram.api.dsl.replyKeyboardMarkup
import com.shopping_list.lib.telegram.api.dsl.row
import com.shopping_list.common.models.UserId
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import ru.ktglib.transport.models.KeyboardButtonRequestUser
import com.shopping_list.response.Response
import ru.shopping_list.sender_service.jsonHelper

suspend fun HttpClient.sendWelcomeMessage(chatId: UserId): Response {
    val response = this.post("sendMessage") {
        contentType(ContentType.Application.Json)
        setBody(message {
            this.chatId = chatId.toLong()
            text = "Этот бот помогает создавать список покупок и управлять им.\n" +
                    "Отправь сообщение, чтобы добавить запись в список.\n"
            replyMarkup = replyKeyboardMarkup {
                oneTimeKeyboard = true
                resizeKeyboard = true
                row {
                    button {
                        text = "Создать общий список"
                        requestUser = KeyboardButtonRequestUser(requestId = 52, userIsBot = false, userIsPremium = null)
                    }
                }
            }
        })
    }
    return jsonHelper().decodeFromString(response.bodyAsText())
}