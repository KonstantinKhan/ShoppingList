package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.`shopping-list`.ShoppingListId
import com.shopping_list_bot.common.models.UserId
import com.shopping_list_bot.repo.shopping_list.DbStateContextRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.createStateContext(title: String) = worker {
    this.title = title
    on {
        shoppingList.id != ShoppingListId.NONE && shoppingList.user.userId != UserId.NONE
    }
    handle {
        val result = shoppingListRepo.createStateContext(DbStateContextRequest(shoppingList.user.userId, shoppingList.id))
        shoppingList = shoppingList.copy(result.shoppingListId)
    }
}