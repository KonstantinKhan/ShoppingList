package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbSharedShoppingListRequest
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.DbUserIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.editMessage(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.readSharedData(DbShoppingListIdRequest(shoppingList.id)).sharedShoppingLists.takeIf {
            it.isNotEmpty()
        }?.let { lists ->
            lists.forEach { list ->
                shoppingListRepo.readState(DbUserIdRequest(list.user.userId)).let {
//                    httpClient.editMessage(
//                        this.copy(
//                            shoppingList = shoppingList.copy(user = shoppingList.user.copy(it.userId)),
//                            messageId = it.messageId
//                        )
//                    )
                }
            }
        }

//        httpClient.editMessage(this)
    }
}