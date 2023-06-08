package com.shopping_list

import com.shopping_list.common.context.BeContext
import com.shopping_list.response.Response

interface IHttpClient {
    suspend fun sendWelcomeMessage(context: BeContext): Response
    suspend fun sendInviteMessage(context: BeContext): Response
    suspend fun getChat(context: BeContext): Response
    suspend fun deleteMessage(context: BeContext)
    suspend fun editMessage(context: BeContext): Response
    suspend fun sendPreInviteMessage(context: BeContext): Response
    suspend fun forwardMessage(context: BeContext): Response
    suspend fun getMe(): Response

    companion object NONE : IHttpClient {
        override suspend fun sendWelcomeMessage(context: BeContext): Response {
            TODO("Not yet implemented")
        }

        override suspend fun sendInviteMessage(context: BeContext): Response {
            TODO("Not yet implemented")
        }

        override suspend fun getChat(context: BeContext): Response {
            TODO("Not yet implemented")
        }

        override suspend fun deleteMessage(context: BeContext) {
            TODO("Not yet implemented")
        }

        override suspend fun editMessage(context: BeContext): Response {
            TODO("Not yet implemented")
        }

        override suspend fun sendPreInviteMessage(context: BeContext): Response {
            TODO("Not yet implemented")
        }

        override suspend fun forwardMessage(context: BeContext): Response {
            TODO("Not yet implemented")
        }

        override suspend fun getMe(): Response {
            TODO("Not yet implemented")
        }
    }
}