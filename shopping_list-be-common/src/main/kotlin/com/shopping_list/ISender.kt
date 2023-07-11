package com.shopping_list

import com.shopping_list.common.context.BeContext
import com.shopping_list.response.TgResponse

interface ISender {

    suspend fun sendWelcomeMessage(context: BeContext): TgResponse
    suspend fun sendCurrentShoppingList(context: BeContext): TgResponse

    companion object NONE : ISender {
        override suspend fun sendWelcomeMessage(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }

        override suspend fun sendCurrentShoppingList(context: BeContext): TgResponse {
            TODO("Not yet implemented")
        }
    }
}