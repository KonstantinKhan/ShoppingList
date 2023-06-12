package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.TgUser
import com.shopping_list.repo.shopping_list.DbPurchaseRequest
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.checkPurchase(title: String) = worker {
    this.title = title
    handle {

        shoppingListRepo.readSharedData(DbShoppingListIdRequest(shoppingList.id)).sharedShoppingLists.takeIf {
            it.isNotEmpty()
        }?.let { lists ->
            lists.forEach { list ->
                shoppingListRepo.togglePurchase(
                    DbPurchaseRequest(
                        purchaseList,
                        list.id,
                        list.user.userId
                    )
                )
            }
        }

        shoppingListRepo.togglePurchase(
            DbPurchaseRequest(
                purchaseList,
                dbShoppingList.id,
                dbShoppingList.user.userId
            )
        ).result?.let {
            shoppingList = dbShoppingList.copy(purchaseList = it.purchaseList)
        }

        shoppingListRepo.readSharedState(DbShoppingListIdRequest(shoppingList.id)).states.forEach {
            httpClient.editMessage(
                this.copy(
                    messageId = it.messageId,
                    shoppingList = shoppingList.copy(user = TgUser(it.userId, ""))
                )
            )
        }
    }
}