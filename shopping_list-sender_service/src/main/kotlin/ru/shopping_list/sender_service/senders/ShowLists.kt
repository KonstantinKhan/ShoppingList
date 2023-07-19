package ru.shopping_list.sender_service.senders

import com.shopping_list.common.context.BeContext
import com.shopping_list.lib.telegram.api.dsl.button
import com.shopping_list.lib.telegram.api.dsl.inlineKeyboardMarkup
import com.shopping_list.lib.telegram.api.dsl.message
import com.shopping_list.lib.telegram.api.dsl.row
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString

//suspend fun HttpClient.showLists(context: BeContext): Response = jsonHelper().decodeFromString(
//    this.post("sendMessage") {
//        contentType(ContentType.Application.Json)
//        setBody(
//            message {
//                chatId = context.shoppingList.user.userId.toLong()
//                text = "Текущий список \uD83D\uDC49 ${context.shoppingList.title} \n" +
//                        "Ваши списки \uD83D\uDC47"
//                replyMarkup = inlineKeyboardMarkup {
//                    context.shoppingLists.map {
//                        row {
//                            button {
//                                text =
//                                    "${if (it.relatedLists.isNotEmpty()) "\uD83D\uDD17" else ""} ${it.title.toString()}"
//                                callbackData = "${it.id.asUUID()}"
//                            }
//                        }
//                    }
//                }
//            }
//        )
//    }.bodyAsText()
//)