package com.shopping_list_bot.repo.shopping_list

interface IRepoShoppingList {
    suspend fun createShoppingList(request: DbShoppingListRequest): DbShoppingListResponse
    suspend fun readShoppingList(request: DbShoppingListIdRequest): DbShoppingListResponse
    suspend fun addPurchase(request: DbPurchaseModelRequest): DbShoppingListResponse
    suspend fun delete(request: DbUserIdRequest): DbShoppingListResponse
    suspend fun deletePurchase(request: DbPurchaseModelRequest): DbShoppingListResponse
    suspend fun updateShoppingList(request: DbShoppingListRequest): DbShoppingListResponse
    suspend fun readMessageId(request: DBMessageIdRequest): DbMessageIdResponse
    suspend fun createMessageId(request: DBMessageIdRequest): DbMessageIdResponse
    suspend fun readStateContext(request: DbUserIdRequest): DbStateContextResponse
    suspend fun updateStateContext(request: DbStateContextRequest): DbStateContextResponse

    companion object NONE : IRepoShoppingList {

        override suspend fun createShoppingList(request: DbShoppingListRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readShoppingList(request: DbShoppingListIdRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun addPurchase(request: DbPurchaseModelRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun delete(request: DbUserIdRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun deletePurchase(request: DbPurchaseModelRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun updateShoppingList(request: DbShoppingListRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readMessageId(request: DBMessageIdRequest): DbMessageIdResponse {
            TODO("Not yet implemented")
        }

        override suspend fun createMessageId(request: DBMessageIdRequest): DbMessageIdResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readStateContext(request: DbUserIdRequest): DbStateContextResponse {
            TODO("Not yet implemented")
        }

        override suspend fun updateStateContext(request: DbStateContextRequest): DbStateContextResponse {
            TODO("Not yet implemented")
        }
    }
}