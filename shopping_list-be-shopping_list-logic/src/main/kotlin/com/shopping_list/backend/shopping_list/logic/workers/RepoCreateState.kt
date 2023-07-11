package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.repo.shopping_list.DbStateRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.repoCreateState(title: String) = worker {
    this.title = title
    on { action == Action.NONE }
    handle {
        shoppingListRepo.createState(
            DbStateRequest(
                shoppingList.user.userId,
                shoppingList.id,
                action = Action.UPDATE_PURCHASE_LIST
            )
        )
    }
}