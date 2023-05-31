package com.shopping_list_bot.http

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.Response

interface IHttpClient {

    suspend fun sendWelcomeMessage(context: BeContextShoppingList): Response
    suspend fun sendCurrentShoppingList(context: BeContextShoppingList): Response
    suspend fun deleteMessage(context: BeContextShoppingList)
    suspend fun editMessage(context: BeContextShoppingList): Response
    suspend fun sendPreInviteMessage(context: BeContextShoppingList): Response
    suspend fun forwardMessage(context: BeContextShoppingList): Response

    companion object NONE : IHttpClient {

        override suspend fun sendWelcomeMessage(context: BeContextShoppingList): Response {
            TODO("Not yet implemented")
        }

        override suspend fun sendCurrentShoppingList(context: BeContextShoppingList): Response {
            TODO("Not yet implemented")
        }

        override suspend fun deleteMessage(context: BeContextShoppingList) {
            TODO("Not yet implemented")
        }

        override suspend fun editMessage(context: BeContextShoppingList): Response {
            TODO("Not yet implemented")
        }

        override suspend fun sendPreInviteMessage(context: BeContextShoppingList): Response {
            TODO("Not yet implemented")
        }

        override suspend fun forwardMessage(context: BeContextShoppingList): Response {
            TODO("Not yet implemented")
        }
    }
}