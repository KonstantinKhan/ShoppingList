import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.repo.shopping_list.*
import org.ehcache.Cache
import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheManagerBuilder
import org.ehcache.config.builders.ExpiryPolicyBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import java.time.Duration

class RepoShoppingListInMemory(
    initObjects: List<ShoppingListModel> = emptyList()
) : IRepoShoppingList {
    private val cache: Cache<String, ShoppingListRow> = let {
        val cacheManager = CacheManagerBuilder
            .newCacheManagerBuilder()
            .build(true)
        cacheManager.createCache(
            "shopping_list-cache",
            CacheConfigurationBuilder
                .newCacheConfigurationBuilder(
                    String::class.java,
                    ShoppingListRow::class.java,
                    ResourcePoolsBuilder
                        .heap(100)
                )
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofMinutes(60)))
                .build()
        )
    }

    private val cacheMessageId: Cache<String, MessageIdRow> = let {
        val cacheManager = CacheManagerBuilder
            .newCacheManagerBuilder()
            .build(true)
        cacheManager.createCache(
            "message_id-cache",
            CacheConfigurationBuilder
                .newCacheConfigurationBuilder(
                    String::class.java,
                    MessageIdRow::class.java,
                    ResourcePoolsBuilder.heap(100)
                )
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofMinutes(60)))
                .build()
        )
    }

    private fun saveMessageId(request: DBMessageIdRequest): DbMessageIdResponse {
        val row = MessageIdRow(request.userId, request.messageId)
        cacheMessageId.put(request.userId.toString(), row)
        return DbMessageIdResponse(result = row.toInternal())
    }

    override suspend fun readMessageId(request: DBMessageIdRequest) =
        cacheMessageId.get(request.userId.toString())?.let {
            DbMessageIdResponse(it.messageId)
        } ?: DbMessageIdResponse(result = MessageId.NONE)

    override suspend fun createMessageId(request: DBMessageIdRequest): DbMessageIdResponse =
        saveMessageId(request)

    override suspend fun createStateContext(request: DbStateRequest): DbStateResponse {
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

    suspend fun updateMessageId(request: DBMessageIdRequest) {
        saveMessageId(request)
    }

    private fun save(item: ShoppingListModel): DbShoppingListResponse {
        val row = ShoppingListRow(item)
        cache.put(row.userId.toString(), row)
        println("=Список=")
        return DbShoppingListResponse(
            result = row.toInternal()
        )
    }

    override suspend fun createShoppingList(request: DbShoppingListRequest): DbShoppingListResponse =
        save(request.shoppingList)

    override suspend fun readShoppingList(request: DbShoppingListIdRequest): DbShoppingListResponse = TODO()
    override suspend fun readShoppingLists(request: DbUserIdRequest): DbShoppingListsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun createPurchase(request: DbPurchaseModelRequest): DbShoppingListResponse {
        TODO("Not yet implemented")
    }
//        cache.get(request.userId.toInt().toString())?.let { DbShoppingListResponse(result = it.toInternal()) }
//            ?: DbShoppingListResponse(
//                result = null
//            )
    override suspend fun togglePurchase(request: DbPurchaseRequest): DbShoppingListResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCheckedPurchases(request: DbStateRequest): DbShoppingListResponse {
        TODO("Not yet implemented")
    }

    override suspend fun delete(request: DbUserIdRequest): DbShoppingListResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deletePurchase(request: DbPurchaseRequest): DbShoppingListResponse = TODO()
//        cache.get(request.userId.toInt().toString())?.let {
//            save(
//                it.apply {
//                    shoppingList.remove(request.purchase)
//                }.toInternal()
//            )
//        } ?: DbShoppingListResponse(
//            result = null
//        )

    override suspend fun updateShoppingList(request: DbShoppingListRequest): DbShoppingListResponse =
        save(request.shoppingList)
}
