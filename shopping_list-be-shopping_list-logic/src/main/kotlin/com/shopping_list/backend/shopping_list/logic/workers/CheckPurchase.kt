package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.TgUser
import com.shopping_list.repo.shopping_list.DbPurchaseRequest
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.DbShoppingListRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.checkPurchase(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.updateShoppingList(
            DbShoppingListRequest(
                dbShoppingList,
                dbShoppingList.purchaseList.filter { messageText.lines().contains(it.name) }
                    .map { it.copy(checked = !it.checked) }
            )).result.let {
            dbShoppingList = it
        }

//        shoppingListRepo.togglePurchase(
//            DbPurchaseRequest(
//                messageText.lines(),
//                dbShoppingList.id,
//                dbShoppingList.user.userId
//            )
//        ).result?.let {
//            println("purchases: ${it.purchaseList}")
//            shoppingList = dbShoppingList.copy(purchaseList = it.purchaseList)
//        }

//        shoppingListRepo.readSharedState(DbShoppingListIdRequest(shoppingList.id)).states.forEach {
//            httpClient.editMessage(
//                this.copy(
//                    messageId = it.messageId,
//                    shoppingList = shoppingList.copy(user = TgUser(it.userId, ""))
//                )
//            )
//        }
    }
}