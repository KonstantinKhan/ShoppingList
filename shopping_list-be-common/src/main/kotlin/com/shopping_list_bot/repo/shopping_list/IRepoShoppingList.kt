package com.shopping_list_bot.repo.shopping_list

interface IRepoShoppingList {
    suspend fun createShoppingList(request: DbShoppingListRequest): DbShoppingListResponse
    suspend fun readShoppingList(request: DbUserIdRequest): DbShoppingListResponse
    suspend fun readShoppingLists(request: DbUserIdRequest): DbShoppingListsResponse
    suspend fun createPurchase(request: DbPurchaseModelRequest): DbShoppingListResponse
    suspend fun togglePurchase(request: DbPurchaseRequest): DbShoppingListResponse
    suspend fun deleteCheckedPurchases(request: DbStateContextRequest): DbShoppingListResponse
    suspend fun delete(request: DbUserIdRequest): DbShoppingListResponse
    suspend fun deletePurchase(request: DbPurchaseRequest): DbShoppingListResponse
    suspend fun updateShoppingList(request: DbShoppingListRequest): DbShoppingListResponse
    suspend fun readMessageId(request: DBMessageIdRequest): DbMessageIdResponse
    suspend fun createMessageId(request: DBMessageIdRequest): DbMessageIdResponse
    suspend fun createStateContext(request: DbStateContextRequest): DbStateContextResponse
    suspend fun readStateContext(request: DbUserIdRequest): DbStateContextResponse
    suspend fun updateStateContext(request: DbStateContextRequest): DbStateContextResponse
    suspend fun clearShoppingList(request: DbStateContextRequest): DbShoppingListResponse
    suspend fun shareShoppingList(request: DbSharedShoppingListRequest): DbShoppingListResponse
    suspend fun readSharedData(request: DbShoppingListIdRequest): DbSharedShoppingList

    companion object NONE : IRepoShoppingList {

        override suspend fun createShoppingList(request: DbShoppingListRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readShoppingList(request: DbUserIdRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readShoppingLists(request: DbUserIdRequest): DbShoppingListsResponse {
            TODO("Not yet implemented")
        }

        override suspend fun createPurchase(request: DbPurchaseModelRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun togglePurchase(request: DbPurchaseRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCheckedPurchases(request: DbStateContextRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun delete(request: DbUserIdRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun deletePurchase(request: DbPurchaseRequest): DbShoppingListResponse {
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

        override suspend fun createStateContext(request: DbStateContextRequest): DbStateContextResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readStateContext(request: DbUserIdRequest): DbStateContextResponse {
            TODO("Not yet implemented")
        }

        override suspend fun updateStateContext(request: DbStateContextRequest): DbStateContextResponse {
            TODO("Not yet implemented")
        }

        override suspend fun clearShoppingList(request: DbStateContextRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun shareShoppingList(request: DbSharedShoppingListRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readSharedData(request: DbShoppingListIdRequest): DbSharedShoppingList {
            TODO("Not yet implemented")
        }
    }
}