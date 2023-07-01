package ru.shopping_list.backend.shopping_list.logic

import com.shopping_list.backend.shopping_list.logic.ShoppingListProcessor
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.context.ShoppingListContextConfig
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.IRepoShoppingList
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.shopping_list.be.repo.test.RepoShoppingListUpdateTest

class ShoppingListClearTest : ShouldSpec() {
    private val repo: IRepoShoppingList =
        SQLTestCompanion.repoUnderTestContainer(
            RepoShoppingListUpdateTest.initShoppingListModels,
            RepoShoppingListUpdateTest.initStates,
            RepoShoppingListUpdateTest.initSharedData
        )
    val processor = ShoppingListProcessor(ShoppingListContextConfig(repoShoppingListInMemory = repo))
    val context =
        BeContext(shoppingList = ShoppingListModel(user = RepoShoppingListUpdateTest.initShoppingListModels.first().user))

    init {
        should("successfully clear for two linked lists") {
            processor.clear(context)
            val firstList =
                repo.readShoppingList(DbShoppingListIdRequest(RepoShoppingListUpdateTest.shoppingListFirst.id)).result
            val secondList =
                repo.readShoppingList(DbShoppingListIdRequest(RepoShoppingListUpdateTest.shoppingListSecond.id)).result
            secondList.purchaseList shouldBe firstList.purchaseList
        }
    }
}