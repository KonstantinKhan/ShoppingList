package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.UserId
import com.shopping_list.repo.shopping_list.DbStateRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.createStateContext(title: String) = worker {
    this.title = title
    on {
        shoppingList.id != ShoppingListId.NONE && shoppingList.user.userId != UserId.NONE
    }
    handle {
        val result = shoppingListRepo.createState(
            DbStateRequest(
                shoppingList.user.userId,
                shoppingList.id,
                action = Action.PURCHASE
            )
        )
        shoppingList = shoppingList.copy(result.shoppingListId)
    }
}