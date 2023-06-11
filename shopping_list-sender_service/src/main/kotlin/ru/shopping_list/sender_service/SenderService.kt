package ru.shopping_list.sender_service

import com.shopping_list.IHttpClient
import com.shopping_list.common.context.BeContext
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import com.shopping_list.response.Response
import ru.shopping_list.sender_service.senders.*

class SenderService(
    private val baseUrl: String
) : IHttpClient {

    private val client: HttpClient = HttpClient(CIO) {
        defaultRequest {
            url(baseUrl)
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                encodeDefaults = false
            })
        }
    }

    override suspend fun sendWelcomeMessage(context: BeContext): Response =
        client.sendWelcomeMessage(context.shoppingList.user.userId)

    override suspend fun sendCurrentShoppingList(context: BeContext): Response =
        client.sendCurrentShoppingList(context)

    override suspend fun sendError(context: BeContext): Response  = client.sendError(context)

    override suspend fun deleteMessage(context: BeContext) =
        client.deleteMessage(context.shoppingList.user.userId, context.messageId)

    override suspend fun editMessage(context: BeContext): Response =
        client.editMessage(context)

    override suspend fun sendPreInviteMessage(context: BeContext): Response =
        client.sendPreInviteMessage(context.shoppingList.user.userId)

    override suspend fun sendInviteMessage(context: BeContext): Response =
        client.sendInviteMessage(context.shoppingList.user.userId, context.bot.userName ?: "")

    override suspend fun forwardMessage(context: BeContext): Response = client.forwardMessage(context)
    override suspend fun getMe(): Response = client.getMe()

    override suspend fun getChat(context: BeContext): Response = client.getChat(context)
}
