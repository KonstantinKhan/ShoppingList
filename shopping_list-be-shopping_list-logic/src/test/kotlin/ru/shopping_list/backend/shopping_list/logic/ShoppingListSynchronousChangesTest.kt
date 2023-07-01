package ru.shopping_list.backend.shopping_list.logic

import com.shopping_list.backend.shopping_list.logic.ShoppingListProcessor
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.context.ShoppingListContextConfig
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.IRepoShoppingList
import io.kotest.core.spec.style.ShouldSpec
import ru.shopping_list.be.repo.test.RepoShoppingListUpdateTest

class ShoppingListSynchronousChangesTest : ShouldSpec() {
    private val repo: IRepoShoppingList =
        SQLTestCompanion.repoUnderTestContainer(
            RepoShoppingListUpdateTest.initShoppingListModels,
            RepoShoppingListUpdateTest.initStates,
            listOf(
                RepoShoppingListUpdateTest.initSharedData,
                listOf(
                    Pair(
                        RepoShoppingListUpdateTest.shoppingListSecond.id,
                        RepoShoppingListUpdateTest.shoppingListThird.id
                    )
                )
            ).flatten()
        )
    val processor = ShoppingListProcessor(ShoppingListContextConfig(repoShoppingListInMemory = repo))
    val context =
        BeContext(
            messageText = "Purchase 2",
            shoppingList = ShoppingListModel(user = RepoShoppingListUpdateTest.initShoppingListModels[2].user)
        )

    init {
        should("successfully clear for two linked lists") {

//            processor.addPurchase(context)
            processor.checkPurchase(context.copy(messageText = "Purchase"))

            repo.readShoppingList(DbShoppingListIdRequest(RepoShoppingListUpdateTest.shoppingListFirst.id)).result.let {
                println("first: ${it.purchaseList}")
            }
            val secondList =
                repo.readShoppingList(DbShoppingListIdRequest(RepoShoppingListUpdateTest.shoppingListSecond.id)).result
            val thirdList =
                repo.readShoppingList(DbShoppingListIdRequest(RepoShoppingListUpdateTest.shoppingListThird.id)).result
            println("second: ${secondList.purchaseList}")
            println("third: ${thirdList.purchaseList}")
        }
    }
}