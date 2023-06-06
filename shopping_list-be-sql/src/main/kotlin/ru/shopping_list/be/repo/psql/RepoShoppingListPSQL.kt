package ru.shopping_list.be.repo.psql

import com.shopping_list.common.context.StateContext
import com.shopping_list.common.models.*
import com.shopping_list.common.models.shopping_list.PurchaseModel
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.common.models.shopping_list.ShoppingListTitle
import com.shopping_list.repo.shopping_list.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.collections.ArrayList

class RepoShoppingListPSQL(
    url: String = "jdbc:postgresql://localhost:5432/shoppinglistdb",
    driver: String = "org.postgresql.Driver",
    user: String = "postgres",
    password: String = "admin",
    initObjects: Collection<ShoppingListModel> = emptyList(),
) : IRepoShoppingList {

    private val db by lazy { SqlConnector(url, driver, user, password).connect() }

    init {
        runBlocking {
            initObjects.forEach { shoppingList ->
                transaction(db) {
                    TgUsersTable.insert {
                        it[id] = shoppingList.user.userId.toInt()
                        it[firstName] = shoppingList.user.firstName
                        it[lastName] = shoppingList.user.lastName
                        it[userName] = shoppingList.user.userName
                    }
                    ShoppingListTable.insert {
                        it[id] = shoppingList.id.asUUID()
                        it[userId] = shoppingList.user.userId.toInt()
                        it[title] = shoppingList.title.toString()
                    }
                    shoppingList.purchaseList.forEach { purchase ->
                        PurchaseTable.insert {
                            it[shoppingListId] = shoppingList.id.asUUID()
                            it[name] = purchase.name
                            it[checked] = purchase.checked
                        }
                    }
                    StateTable.insert {
                        it[userId] = shoppingList.user.userId.toInt()
                        it[shoppingListId] = shoppingList.id.asUUID()
                        it[lastMessageId] = -1
                    }
                    ShoppingListTable.selectAll().forEach {
                        println("${it[ShoppingListTable.userId]} : ${it[ShoppingListTable.title]} : ${it[ShoppingListTable.id]}")
                    }
                }
            }
        }
    }

    private fun save(request: DbShoppingListRequest): DbShoppingListResponse {
        return transaction(db) {
            with(request.shoppingList) {
                TgUsersTable.insertIgnore {
                    it[id] = user.userId.toInt()
                    it[firstName] = user.firstName
                    it[lastName] = user.lastName
                    it[userName] = user.userName
                }
                val shoppingListIdComputed = ShoppingListTable.insertIgnore {
                    if (this@with.id != ShoppingListId.NONE)
                        it[id] = this@with.id.asUUID()
                    it[title] = this@with.title.toString()
                    it[userId] = user.userId.toInt()

                } get ShoppingListTable.id

                purchaseList.forEach { purchase ->
                    PurchaseTable.insert {
                        it[shoppingListId] = shoppingListIdComputed
                        it[name] = purchase.name
                        it[checked] = purchase.checked
                    }
                }

                val purchaseList = ArrayList(PurchaseTable.select {
                    PurchaseTable.shoppingListId eq shoppingListIdComputed
                }.toList().map {
                    PurchaseModel(
                        name = it[PurchaseTable.name], checked = it[PurchaseTable.checked]
                    )
                })

                println("DB contains:")
                ShoppingListTable.selectAll().forEach {
                    println("${it[ShoppingListTable.userId]} : ${it[ShoppingListTable.title]} : ${it[ShoppingListTable.id]}")
                }

                DbShoppingListResponse(
                    result = ShoppingListModel(
                        id = ShoppingListId(shoppingListIdComputed.toString()),
                        user = user,
                        purchaseList = purchaseList
                    )
                )
            }
        }
    }

    override suspend fun createShoppingList(request: DbShoppingListRequest): DbShoppingListResponse = save(request)

    override suspend fun readShoppingList(request: DbShoppingListIdRequest): DbShoppingListResponse {

        return transaction(db) {

            val purchaseList = PurchaseTable.select {
                PurchaseTable.shoppingListId eq request.shoppingListId.asUUID()
            }.orderBy(PurchaseTable.checked to SortOrder.ASC, PurchaseTable.name to SortOrder.ASC)
                .map {
                    PurchaseModel(
                        it[PurchaseTable.name],
                        it[PurchaseTable.checked]
                    )
                }

            val prototypeShoppingLists = SharedShoppingListTable.select {
                SharedShoppingListTable.duplicateShoppingList eq request.shoppingListId.asUUID()
            }.takeIf { !it.empty() }?.let { query ->
                query.map { ShoppingListId(it[SharedShoppingListTable.sourceShoppingList]) }
            } ?: emptyList()

            val derivativeShoppingList = SharedShoppingListTable.select {
                SharedShoppingListTable.sourceShoppingList eq request.shoppingListId.asUUID()
            }.takeIf { !it.empty() }?.let { query ->
                query.map { ShoppingListId(it[SharedShoppingListTable.duplicateShoppingList]) }
            } ?: emptyList()

            ShoppingListTable
                .join(
                    TgUsersTable,
                    JoinType.INNER,
                    additionalConstraint = { TgUsersTable.id eq ShoppingListTable.userId })
                .select { ShoppingListTable.id eq request.shoppingListId.asUUID() }.takeIf { !it.empty() }?.let {
                    with(it.first()) {
                        DbShoppingListResponse(
                            ShoppingListModel(
                                id = ShoppingListId(this[ShoppingListTable.id]),
                                title = ShoppingListTitle(this[ShoppingListTable.title]),
                                user = TgUser(
                                    UserId(this[TgUsersTable.id]),
                                    this[TgUsersTable.firstName],
                                    this[TgUsersTable.lastName],
                                    this[TgUsersTable.userName]
                                ),
                                purchaseList,
                                prototypeShoppingLists,
                                derivativeShoppingList
                            )
                        )
                    }
                } ?: DbShoppingListResponse()
        }
    }

    override suspend fun readShoppingLists(request: DbUserIdRequest): DbShoppingListsResponse {
        return transaction(db) {
            ShoppingListTable.select { ShoppingListTable.userId eq request.userId.toInt() }.takeIf { !it.empty() }
                ?.let { query ->
                    DbShoppingListsResponse(query.map { ShoppingListId(it[ShoppingListTable.id]) })
                } ?: DbShoppingListsResponse(emptyList())
        }
    }

    override suspend fun createPurchase(request: DbPurchaseModelRequest): DbShoppingListResponse {
        return transaction(db) {
            PurchaseTable.batchInsert(request.purchase) {
                this[PurchaseTable.shoppingListId] = request.shoppingListId.asUUID()
                this[PurchaseTable.name] = it.name
                this[PurchaseTable.checked] = it.checked
            }
            val computedPurchaseList = PurchaseTable.select {
                PurchaseTable.shoppingListId eq request.shoppingListId.asUUID()
            }.orderBy(PurchaseTable.checked to SortOrder.ASC, PurchaseTable.name to SortOrder.ASC)
                .map { PurchaseModel(name = it[PurchaseTable.name], checked = it[PurchaseTable.checked]) }
            DbShoppingListResponse(
                ShoppingListModel(
                    id = ShoppingListId(request.shoppingListId.asUUID()),
                    purchaseList = computedPurchaseList
                )
            )
        }
    }

    override suspend fun togglePurchase(request: DbPurchaseRequest): DbShoppingListResponse {
        return transaction(db) {
            val computedChecked =
                PurchaseTable.select {
                    Op.build {
                        PurchaseTable.shoppingListId eq request.shoppingListId.asUUID() and
                                (PurchaseTable.name inList request.purchase)
                    }
                }.associate { it[PurchaseTable.name] to it[PurchaseTable.checked] }

            computedChecked.forEach { pair ->
                PurchaseTable.update({
                    Op.build {
                        PurchaseTable.shoppingListId eq request.shoppingListId.asUUID() and
                                (PurchaseTable.name eq pair.key)
                    }
                }) {
                    it[checked] = !pair.value
                }
            }

            val purchaseList = PurchaseTable.select {
                PurchaseTable.shoppingListId eq request.shoppingListId.asUUID()
            }.orderBy(PurchaseTable.checked to SortOrder.ASC, PurchaseTable.name to SortOrder.ASC).map {
                PurchaseModel(
                    it[PurchaseTable.name],
                    it[PurchaseTable.checked]
                )
            }
            DbShoppingListResponse(
                result = ShoppingListModel(
                    request.shoppingListId,
                    purchaseList = purchaseList
                )
            )
        }
    }

    override suspend fun deleteCheckedPurchases(request: DbStateRequest): DbShoppingListResponse {
        return transaction(db) {
            PurchaseTable.deleteWhere {
                Op.build {
                    shoppingListId eq request.shoppingListId.asUUID() and
                            (checked eq true)
                }
            }
            val purchaseList = PurchaseTable.select {
                PurchaseTable.shoppingListId eq request.shoppingListId.asUUID()
            }.orderBy(PurchaseTable.checked to SortOrder.ASC, PurchaseTable.name to SortOrder.ASC)
                .map {
                    PurchaseModel(
                        it[PurchaseTable.name],
                        it[PurchaseTable.checked]
                    )
                }
            DbShoppingListResponse(
                result = ShoppingListModel(
                    purchaseList = purchaseList
                )
            )
        }
    }

    override suspend fun delete(request: DbUserIdRequest): DbShoppingListResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deletePurchase(request: DbPurchaseRequest): DbShoppingListResponse {
        return transaction(db) {
            PurchaseTable.deleteWhere {
                Op.build {
                    shoppingListId eq request.shoppingListId.asUUID() and
                            (name inList request.purchase)
                }
            }
            val user = TgUsersTable.select {
                TgUsersTable.id eq request.userId.toInt()
            }.first().let {
                TgUser(
                    UserId(it[TgUsersTable.id]),
                    it[TgUsersTable.firstName],
                    it[TgUsersTable.lastName],
                    it[TgUsersTable.userName]
                )
            }

            val purchaseList = PurchaseTable.select {
                PurchaseTable.shoppingListId eq request.shoppingListId.asUUID()
            }.map {
                PurchaseModel(
                    it[PurchaseTable.name],
                    it[PurchaseTable.checked]
                )
            }

            val title = ShoppingListTable.select {
                ShoppingListTable.id eq request.shoppingListId.asUUID()
            }.first().let { it[ShoppingListTable.title] }

            DbShoppingListResponse(
                result = ShoppingListModel(
                    request.shoppingListId,
                    ShoppingListTitle(title),
                    user,
                    purchaseList
                )
            )
        }
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

    override suspend fun createStateContext(request: DbStateRequest): DbStateResponse {
        return transaction(db) {
            try {
                StateTable.insert {
                    it[userId] = request.userId.toInt()
                    it[shoppingListId] = request.shoppingListId.asUUID()
                    it[lastMessageId] = request.messageId.toInt()
                }
            } catch (e: Exception) {
                println("exception: $e")
            }
            DbStateResponse(
                request.userId,
                request.shoppingListId
            )
        }
    }

    override suspend fun readState(request: DbUserIdRequest): DbStateResponse {
        return transaction(db) {
            val result = StateTable
                .join(
                    SharedShoppingListTable,
                    JoinType.INNER,
                    additionalConstraint = { SharedShoppingListTable.sourceShoppingList eq StateTable.shoppingListId })
                .join(
                    ShoppingListTable,
                    JoinType.INNER,
                    additionalConstraint = { ShoppingListTable.id eq SharedShoppingListTable.duplicateShoppingList })
                .join(TgUsersTable,
                    JoinType.INNER,
                    additionalConstraint = { ShoppingListTable.userId eq TgUsersTable.id })
                .slice(TgUsersTable.userName)
                .select { StateTable.userId eq request.userId.toInt() }
            result.forEach {
                println("row result: ${it[TgUsersTable.userName]}")
            }

            with(StateTable.select {
                StateTable.userId eq request.userId.toInt()
            }.takeIf { !it.empty() }?.let { query ->
                query.first().let { row ->
                    StateContext(
                        request.userId,
                        ShoppingListId(row[StateTable.shoppingListId]),
                        MessageId(row[StateTable.lastMessageId])
                    )
                }
            }) {
                DbStateResponse(
                    userId = request.userId,
                    shoppingListId = this?.shoppingListId ?: ShoppingListId.NONE,
                    messageId = this?.messageId ?: MessageId.NONE
                )
            }
        }
    }

    override suspend fun updateStateContext(request: DbStateRequest): DbStateResponse {
        return transaction(db) {
            StateTable.update({ StateTable.userId eq request.userId.toInt() }) {
                it[lastMessageId] = request.messageId.toInt()
            }
            DbStateResponse(request.userId, request.shoppingListId, request.messageId)
        }
    }

    override suspend fun clearShoppingList(request: DbStateRequest): DbShoppingListResponse {
        return transaction(db) {
            PurchaseTable.deleteWhere { shoppingListId eq request.shoppingListId.asUUID() }
            with(PurchaseTable.select { PurchaseTable.shoppingListId eq request.shoppingListId.asUUID() }) {
                if (this.empty())
                    DbShoppingListResponse()
                else throw Exception("The delete method does not work correctly")
            }
        }
    }

    override suspend fun shareShoppingList(request: DbSharedShoppingListRequest): DbShoppingListResponse {
        return transaction(db) {
            SharedShoppingListTable.select {
                Op.build {
                    SharedShoppingListTable.sourceShoppingList eq request.sourceShoppingList.asUUID() and
                            (SharedShoppingListTable.duplicateShoppingList
                                    inList request.shoppingListsOfUserConsumer.map { it.asUUID() })
                }
            }.takeIf { it.empty() }?.let {
                val computedShoppingList = ShoppingListTable.insert {
                    it[title] = "Связанный список"
                    it[userId] = request.userConsumer.userId.toInt()
                } get ShoppingListTable.id

                SharedShoppingListTable.insert {
                    it[sourceShoppingList] = request.sourceShoppingList.asUUID()
                    it[duplicateShoppingList] = computedShoppingList
                }
                val purchaseList =
                    PurchaseTable.select { PurchaseTable.shoppingListId eq request.sourceShoppingList.asUUID() }
                        .takeIf { !it.empty() }?.let { query ->
                            query.map { row ->
                                PurchaseTable.insert {
                                    it[shoppingListId] = computedShoppingList
                                    it[name] = row[name]
                                    it[checked] = row[checked]
                                }
                                PurchaseModel(row[PurchaseTable.name], row[PurchaseTable.checked])
                            }
                        } ?: emptyList()
                DbShoppingListResponse(
                    ShoppingListModel(
                        purchaseList = purchaseList
                    )
                )
            } ?: DbShoppingListResponse(
                ShoppingListModel(
                    purchaseList = listOf(PurchaseModel(name = "There was null"))
                )
            )
        }
    }

    override suspend fun readSharedData(request: DbShoppingListIdRequest): DbSharedShoppingList {
        return transaction(db) {
            val shoppingLists =
                SharedShoppingListTable.select { SharedShoppingListTable.sourceShoppingList eq request.shoppingListId.asUUID() }
                    .takeIf { !it.empty() }?.let { row ->
                        row.map {
                            ShoppingListId(it[SharedShoppingListTable.duplicateShoppingList])
                        }
                    } ?: emptyList()
            DbSharedShoppingList(shoppingLists)
        }
    }
}