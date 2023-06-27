package ru.shopping_list.backend.shopping_list.logic

import com.shopping_list.backend.shopping_list.logic.ShoppingListProcessor
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.context.ShoppingListContextConfig
import com.shopping_list.common.models.TgUser
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.IRepoShoppingList
import io.kotest.common.runBlocking
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import ru.shopping_list.be.repo.test.RepoShoppingListUpdateTest
import ru.shopping_list.be.repo.test.RepoShoppingListUpdateTest.Companion.initSharedData
import ru.shopping_list.be.repo.test.RepoShoppingListUpdateTest.Companion.initShoppingListModels
import ru.shopping_list.be.repo.test.RepoShoppingListUpdateTest.Companion.initStates

class ShoppingListTidyTest : ShouldSpec() {

    private val repo: IRepoShoppingList =
        SQLTestCompanion.repoUnderTestContainer(initShoppingListModels, initStates, initSharedData)
    val processor = ShoppingListProcessor(ShoppingListContextConfig(repoShoppingListInMemory = repo))
    val context =
        BeContext(shoppingList = ShoppingListModel(user = initShoppingListModels.first().user))

    init {
        should("successfully tidy for two linked lists") {
            runBlocking {
                processor.tidy(context)
                val firstList =
                    repo.readShoppingList(DbShoppingListIdRequest(RepoShoppingListUpdateTest.shoppingListFirst.id)).result
                val secondList =
                    repo.readShoppingList(DbShoppingListIdRequest(RepoShoppingListUpdateTest.shoppingListSecond.id)).result
                secondList.purchaseList shouldBe firstList.purchaseList
                println(firstList.purchaseList)
                println(secondList.purchaseList)
            }
        }
    }
}