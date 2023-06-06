package ru.shopping_list.be.repo.test

import com.shopping_list.common.models.*
import com.shopping_list.common.models.shopping_list.PurchaseModel
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.repo.shopping_list.*
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.runBlocking
import java.util.UUID

abstract class ShoppingListTest : ShouldSpec() {
    abstract val repo: IRepoShoppingList

    init {
        should("successfully return none objects") {
            println("1")
            val result = runBlocking { repo.readState(DbUserIdRequest(UserId(111111))) }
            result.userId shouldBe UserId.NONE
            result.shoppingListId shouldBe ShoppingListId.NONE
            result.messageId shouldBe MessageId.NONE
        }
        should("successfully read the state context") {
            println("2")
            val result = runBlocking { repo.readState(DbUserIdRequest(user.userId)) }
            result.userId shouldBe user.userId
            result.shoppingListId shouldNotBe ShoppingListId.NONE
            result.messageId shouldNotBe MessageId.NONE
        }
        should("successfully return an existing shopping list") {
            println("3")
            val result =
                runBlocking { repo.createShoppingList(DbShoppingListRequest(createShoppingList)) }
            result.result shouldBe createShoppingList
        }
        should("successfully create an shopping list with data") {
            println("4")
            val result = runBlocking {
                repo.createShoppingList(
                    DbShoppingListRequest(createShoppingList.copy(ShoppingListId.NONE))
                )
            }
            val expected = createShoppingList.copy(result.result.id)
            result.result shouldBe expected
        }
        should("successfully create an empty shopping list") {
            println("5")
            val result =
                runBlocking {
                    repo.createShoppingList(
                        DbShoppingListRequest(createShoppingList.copy(ShoppingListId.NONE, purchaseList = emptyList()))
                    )
                }
            val expected = createShoppingList.copy(
                result.result.id,
                purchaseList = emptyList()
            )
            result.result shouldBe expected
        }
        should("successfully update a state context with a new message") {
            println("6")
            val result = runBlocking {
                repo.updateStateContext(
                    DbStateRequest(
                        userId = createShoppingList.user.userId,
                        shoppingListId = createShoppingList.id,
                        messageId = MessageId(id = 111)
                    )
                )
            }
            result.userId shouldBe createShoppingList.user.userId
            result.shoppingListId shouldBe shoppingListId
            result.messageId shouldBe MessageId(111)
        }
        should("successfully update a state context with a new message and a news shopping list ") {
            println("7")
            val state = runBlocking { repo.readState(DbUserIdRequest(user.userId)) }
            println("state before userId: ${state.userId}")
            println("state before shoppingListId: ${state.shoppingListId}")
            println("state before messageId: ${state.messageId}")
            val result = runBlocking {
                repo.createShoppingList(DbShoppingListRequest(createShoppingList.copy(randomShoppingListId)))
                repo.updateStateContext(
                    DbStateRequest(
                        userId = createShoppingList.user.userId,
                        shoppingListId = randomShoppingListId,
                        messageId = MessageId(id = 222)
                    )
                )
            }
            println("state after userId: ${result.userId}")
            println("state after shoppingListId: ${result.shoppingListId}")
            println("state after messageId: ${result.messageId}")

            result.userId shouldBe createShoppingList.user.userId
            result.shoppingListId shouldBe randomShoppingListId
            result.messageId shouldBe MessageId(222)
        }
//        should("successfully read the shopping list") {
//            val result = runBlocking { repo.readShoppingList(DbUserIdRequest(userId = user.userId)) }
//            val expected = createShoppingList.copy(randomShoppingListId)
//            result.result shouldBe expected
//        }
        should("successfully add purchase") {
            val result = runBlocking {
                repo.createPurchase(DbPurchaseModelRequest(createShoppingList.id, listOf(PurchaseModel("milk"))))
            }
            val expected =
                createShoppingList.copy(
                    randomShoppingListId,
                    purchaseList = (createShoppingList.purchaseList + PurchaseModel("milk")).sortedBy { it.name })
            println("shopping list: ${result.result}")
            result.result shouldBe expected
        }
        should("successfully toggle the purchase") {
            val result = runBlocking {
                repo.togglePurchase(
                    DbPurchaseRequest(
                        listOf("milk"),
                        randomShoppingListId,
                        user.userId
                    )
                )
            }
            val expected = createShoppingList.copy(
                randomShoppingListId,
                purchaseList = (createShoppingList.purchaseList + PurchaseModel("milk", true))
                    .sortedBy { it.name }
            )
            println("shopping list: ${result.result}")
            result.result shouldBe expected
        }
        should("successfully delete checked purchases") {
            val result = runBlocking {
                repo.deleteCheckedPurchases(
                    DbStateRequest(
                        user.userId,
                        randomShoppingListId,
                        MessageId(333)
                    )
                )
            }
            println("shopping list: ${result.result}")
            val expected = createShoppingList.copy(randomShoppingListId)
            result.result shouldBe expected
        }
        should("successfully delete purchase") {
            val result = runBlocking {
                repo.deletePurchase(
                    DbPurchaseRequest(
                        listOf("Хлеб"),
                        randomShoppingListId,
                        user.userId
                    )
                )
            }
            val expected = createShoppingList.copy(randomShoppingListId, purchaseList = emptyList())
            println("shopping list: ${result.result}")
            result.result shouldBe expected
        }
    }
    companion object : BaseInit() {
        private val user = TgUser(UserId(123456), "first", "last", "user")
        private val shoppingListId = ShoppingListId("72086f29-7915-49db-819f-53507bbc0f8b")
        private val randomShoppingListId = ShoppingListId(UUID.randomUUID())
        private val createShoppingList = ShoppingListModel(
            id = shoppingListId,
            user = user,
            purchaseList = listOf(
                PurchaseModel(
                    name = "Хлеб",
                    checked = false
                )
            )
        )
        override val initObjects: List<ShoppingListModel> = listOf(
            createShoppingList
        )
    }
}