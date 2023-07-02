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
import ru.shopping_list.sender_service.helpers.replaceExt
import ru.shopping_list.sender_service.jsonHelper

suspend fun HttpClient.editMessage(context: BeContext): Response {
    println("edit message: ${context.shoppingList.purchaseList}")
    val response = this.post("editMessageText") {
        contentType(ContentType.Application.Json)
        setBody(editMessageText {
            chatId = context.shoppingList.user.userId.toLong()
            messageId = context.messageId.toInt()
            text =
                "${if (context.shoppingList.relatedLists.isNotEmpty()) "\uD83D\uDD17" else ""} Список покупок: \\\n" +
                        context.shoppingList.purchaseList.joinToString("\n") {
                            if (it.checked) "~${it.name.replaceExt()}~"
                            else it.name.replaceExt()
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

    println("response: ${response.bodyAsText()}")

    return jsonHelper().decodeFromString(response.bodyAsText())
}