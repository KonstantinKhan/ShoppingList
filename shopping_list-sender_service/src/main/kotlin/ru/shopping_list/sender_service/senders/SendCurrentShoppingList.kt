package ru.shopping_list.sender_service.senders

import com.shopping_list.lib.telegram.api.dsl.button
import com.shopping_list.lib.telegram.api.dsl.inlineKeyboardMarkup
import com.shopping_list.lib.telegram.api.dsl.message
import com.shopping_list.lib.telegram.api.dsl.row
import com.shopping_list.common.context.BeContext
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import ru.shopping_list.sender_service.helpers.replaceExt

//suspend fun HttpClient.sendCurrentShoppingList(context: BeContext): Response {
//    val response = this.post("sendMessage") {
//        contentType(ContentType.Application.Json)
//        setBody(message {
//            chatId = context.shoppingList.user.userId.toLong()
//            text = "${if (context.shoppingList.relatedLists.isNotEmpty()) "\uD83D\uDD17" else ""} Список покупок:\\\n" +
//                    context.shoppingList.purchaseList.joinToString("\n") {
//                        if (it.checked) "~${it.name.replaceExt()}~"
//                        else it.name.replaceExt()
//                    }
//            replyMarkup = inlineKeyboardMarkup {
//                context.shoppingList.purchaseList.filter { !it.checked }.map {
//                    row {
//                        button {
//                            text = it.name
//                            callbackData = it.name
//                        }
//                    }
//                }
//            }
//            parseMode = "MarkdownV2"
//        })
//    }
//    return jsonHelper().decodeFromString(response.bodyAsText())
//}

