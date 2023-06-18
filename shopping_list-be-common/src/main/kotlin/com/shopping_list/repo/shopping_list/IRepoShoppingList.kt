package com.shopping_list.repo.shopping_list

interface IRepoShoppingList {
    suspend fun createShoppingList(request: DbShoppingListRequest): DbShoppingListResponse
    suspend fun readShoppingList(request: DbShoppingListIdRequest): DbShoppingListResponse
    suspend fun readShoppingLists(request: DbUserIdRequest): DbShoppingListsIdsResponse
    suspend fun createPurchase(request: DbPurchaseModelRequest): DbShoppingListResponse
    suspend fun deleteCheckedPurchases(request: DbStateRequest): DbShoppingListResponse
    suspend fun delete(request: DbUserIdRequest): DbShoppingListResponse
    suspend fun deletePurchase(request: DbPurchaseRequest): DbShoppingListResponse
    suspend fun updateShoppingList(request: DbShoppingListRequest): DbShoppingListResponse
    suspend fun readMessageId(request: DBMessageIdRequest): DbMessageIdResponse
    suspend fun createMessageId(request: DBMessageIdRequest): DbMessageIdResponse
    suspend fun createState(request: DbStateRequest): DbStateResponse
    suspend fun readState(request: DbUserIdRequest): DbStateResponse
    suspend fun updateStateContext(request: DbStateRequest): DbStateResponse
    suspend fun clearShoppingList(request: DbStateRequest): DbShoppingListResponse
    suspend fun shareShoppingList(request: DbSharedShoppingListRequest): DbShoppingListResponse
    suspend fun readSharedData(request: DbShoppingListIdRequest): DbSharedShoppingList
    suspend fun readSharedState(request: DbShoppingListIdRequest): DbSharedStateResponse
    suspend fun searchShoppingList(request: DbFilterShoppingListRequest): DbShoppingListsResponse

    companion object NONE : IRepoShoppingList {

        override suspend fun createShoppingList(request: DbShoppingListRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readShoppingList(request: DbShoppingListIdRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readShoppingLists(request: DbUserIdRequest): DbShoppingListsIdsResponse {
            TODO("Not yet implemented")
        }

        override suspend fun createPurchase(request: DbPurchaseModelRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCheckedPurchases(request: DbStateRequest): DbShoppingListResponse {
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

        override suspend fun createState(request: DbStateRequest): DbStateResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readState(request: DbUserIdRequest): DbStateResponse {
            TODO("Not yet implemented")
        }

        override suspend fun updateStateContext(request: DbStateRequest): DbStateResponse {
            TODO("Not yet implemented")
        }

        override suspend fun clearShoppingList(request: DbStateRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun shareShoppingList(request: DbSharedShoppingListRequest): DbShoppingListResponse {
            TODO("Not yet implemented")
        }

        override suspend fun readSharedData(request: DbShoppingListIdRequest): DbSharedShoppingList {
            TODO("Not yet implemented")
        }

        override suspend fun readSharedState(request: DbShoppingListIdRequest): DbSharedStateResponse {
            TODO("Not yet implemented")
        }

        override suspend fun searchShoppingList(request: DbFilterShoppingListRequest): DbShoppingListsResponse {
            TODO("Not yet implemented")
        }
    }
}