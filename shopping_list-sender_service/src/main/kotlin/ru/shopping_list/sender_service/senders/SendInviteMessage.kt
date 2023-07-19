package ru.shopping_list.sender_service.senders

import com.shopping_list.common.models.UserId
import com.shopping_list.lib.telegram.api.dsl.message
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString

//suspend fun HttpClient.sendInviteMessage(userId: UserId, botName: String): Response {
//    val response = this.post("sendMessage") {
//        contentType(ContentType.Application.Json)
//        setBody(message {
//            chatId = userId.toLong()
//            text = "Пригласительное сообщение. \n" +
//                    "Подключай бота @$botName для управления списком покупок!"
//        })
//    }
//    return jsonHelper().decodeFromString(response.bodyAsText())
//}