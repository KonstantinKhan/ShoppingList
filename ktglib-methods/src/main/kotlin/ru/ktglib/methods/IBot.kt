package ru.ktglib.methods

import ru.ktglib.transport.models.request.EditMessageTextModel
import ru.ktglib.transport.models.request.SendMessageModel
import ru.ktglib.types.Response

interface IBot {
    suspend fun sendMessage(message: SendMessageModel): Response
    suspend fun editMessageText(message: EditMessageTextModel): Response
}