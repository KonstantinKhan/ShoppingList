package ru.shopping_list.sender_service.senders

import com.shopping_list.common.context.BeContext
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.ktglib.transport.models.request.AnswerCallbackQueryRequest

suspend inline fun HttpClient.answerCallbackQuery(context: BeContext) {
    this.post("answerCallbackQuery") {
        contentType(ContentType.Application.Json)
        setBody(AnswerCallbackQueryRequest(context.queryId))
    }
}