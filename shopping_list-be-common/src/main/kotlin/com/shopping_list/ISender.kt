package com.shopping_list

import com.shopping_list.common.context.BeContext
import com.shopping_list.response.TgResponse

interface ISender {

    suspend fun sendWelcomeMessage(context: BeContext): TgResponse
    suspend fun sendCurrentShoppingList(context: BeContext): TgResponse
    suspend fun sendListTitle(context: BeContext): TgResponse
    suspend fun sendRecipientNotification(context: BeContext): TgResponse
    suspend fun sendPreInviteMessage(context: BeContext): TgResponse
    suspend fun sendInviteMessage(context: BeContext): TgResponse
    suspend fun editCurrentShoppingList(context: BeContext): TgResponse
    suspend fun showLists(context: BeContext): TgResponse
    suspend fun showListsForUpdate(context: BeContext): TgResponse
    suspend fun showListsForDelete(context: BeContext): TgResponse
    suspend fun getChat(context: BeContext): TgResponse
    suspend fun getMe(): TgResponse

    companion object NONE : ISender {
        override suspend fun sendWelcomeMessage(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun sendCurrentShoppingList(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun sendListTitle(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun sendRecipientNotification(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun sendPreInviteMessage(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun sendInviteMessage(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun editCurrentShoppingList(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun showLists(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun showListsForUpdate(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun showListsForDelete(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun getChat(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun getMe(): TgResponse {
            TODO("Not yet implemented")
        }
    }
}