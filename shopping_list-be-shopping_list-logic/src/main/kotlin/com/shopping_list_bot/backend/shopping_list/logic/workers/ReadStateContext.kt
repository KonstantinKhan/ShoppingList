package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.repo.shopping_list.DbUserIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.readStateContext(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.readStateContext(DbUserIdRequest(shoppingList.user.userId)).let {
            messageId = it.messageId
            shoppingList = shoppingList.copy(it.shoppingListId)
        }
    }
}