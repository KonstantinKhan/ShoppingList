package ru.ktglib.methods

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.ktglib.transport.models.request.EditMessageTextModel
import ru.ktglib.transport.models.request.SendMessageModel
import ru.ktglib.types.*

class Bot(private val baseUrl: String) : IBot {

    private val client: HttpClient = HttpClient(CIO) {
        defaultRequest {
            url(baseUrl)
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                encodeDefaults = false
            })
        }
    }

    private val responseModule = SerializersModule {
        polymorphic(Response::class) {
            subclass(ResponseWithMessage.serializer())
        }
    }
    private val resultModule = SerializersModule {
        polymorphic(Result::class) {
            subclass(Message::class)
            subclass(User::class)
        }
    }

    private val json = Json {
        serializersModule = responseModule + resultModule
        isLenient = true
        ignoreUnknownKeys = true
    }

    override suspend fun sendMessage(message: SendMessageModel): Response =
        json.decodeFromString(client.post("sendMessage") {
            setBody(message)
        }.bodyAsText())

    override suspend fun editMessageText(message: EditMessageTextModel): Response =
        json.decodeFromString(client.post("editMessageText") {
            setBody(message)
        }.bodyAsText())
}