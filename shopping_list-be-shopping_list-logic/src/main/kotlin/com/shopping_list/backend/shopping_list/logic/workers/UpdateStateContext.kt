package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbStateRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.updateState(title: String) = worker {
    this.title = title
    handle {
        val result = shoppingListRepo.updateState(
            DbStateRequest(
                userId = shoppingList.user.userId,
                messageId = messageId,
                shoppingListId = shoppingList.id,
                action = action
            )
        )
    }
}