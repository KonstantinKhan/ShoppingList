package ru.shopping_list.sender_service.senders

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString

//suspend fun HttpClient.getMe(): Response {
//    val response = this.get("getMe")
//    println("response in getMe: ${response.bodyAsText()}")
//    return jsonHelper().decodeFromString(response.bodyAsText())
//}