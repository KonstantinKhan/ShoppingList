package ru.shopping_list.sender_service.senders

import com.shopping_list.common.context.BeContext
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import com.shopping_list.response.Response
import ru.shopping_list.sender_service.jsonHelper

suspend fun HttpClient.getChat(context: BeContext): Response {
    val response = this.post("getChat") {
        contentType(ContentType.Application.Json)
        setBody(
            context.recipient.userId
        )
    }
    println("response: ${response.bodyAsText()}")

    return jsonHelper().decodeFromString(response.bodyAsText())
}