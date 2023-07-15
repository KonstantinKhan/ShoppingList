package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.User
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.DbStateRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.sendCurrentShoppingList(title: String) = worker {
    this.title = title
    on { errors.isEmpty() }
    handle {
        if (recipient == User.NONE)
            shoppingListRepo.readSharedData(DbShoppingListIdRequest(shoppingList.id))
                .sharedShoppingLists.takeIf { it.isNotEmpty() }?.let { lists ->
                    lists.forEach { list ->
                        sender.sendCurrentShoppingList(
                            this.copy(
                                shoppingList = shoppingList.copy(
                                    user = list.user,
                                    title = list.title
                                )
                            )
                        ).result.let {
                            shoppingListRepo.updateState(
                                DbStateRequest(
                                    userId = list.user.userId,
                                    shoppingListId = list.id,
                                    messageId = it.messageId,
                                    action = Action.UPDATE_PURCHASE_LIST
                                )
                            )
                        }
                    }
                }
        println("related: ${shoppingList.relatedLists}")
        sender.sendCurrentShoppingList(this).let {
            messageId = it.result.messageId
        }
    }
    except {
        println("error: $it")
    }
}