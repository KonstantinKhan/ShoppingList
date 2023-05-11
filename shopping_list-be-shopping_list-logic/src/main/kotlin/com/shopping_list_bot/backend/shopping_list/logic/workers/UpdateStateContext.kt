package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.repo.shopping_list.DbStateContextRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.updateStateContext(title: String) = worker {
    this.title = title
    handle {
        val result = shoppingListRepo.updateStateContext(
            DbStateContextRequest(
                userId = shoppingList.user.userId,
                messageId = messageId,
                shoppingListId = shoppingList.id
            )
        )
        println("result: ${result.userId}")
        println("result: ${result.shoppingListId}")
        println("result: ${result.messageId}")
    }
}