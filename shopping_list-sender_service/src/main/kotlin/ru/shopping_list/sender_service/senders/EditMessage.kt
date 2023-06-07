package ru.shopping_list.sender_service.senders

import com.shopping_list.lib.telegram.api.dsl.*
import com.shopping_list.common.context.BeContext
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.shopping_list.response.Response

suspend fun HttpClient.editMessage(context: BeContext): Response {
    val response = this.post("editMessageText") {
        contentType(ContentType.Application.Json)
        setBody(editMessageText {
            chatId = context.shoppingList.user.userId.toLong()
            messageId = context.messageId.toInt()
            text = "Список покупок: \\\n" +
                    context.shoppingList.purchaseList.joinToString("\n") {
                        if (it.checked) "~${it.name}~"
                        else it.name
                    }
            replyMarkup = inlineKeyboardMarkup {
                context.shoppingList.purchaseList.filter { !it.checked }.map {
                    row {
                        button {
                            text = it.name
                            callbackData = it.name
                        }
                    }
                }
            }
            parseMode = "MarkdownV2"
        })
    }

    val json = Json {
        ignoreUnknownKeys = true
    }
    return json.decodeFromString(response.bodyAsText())
}