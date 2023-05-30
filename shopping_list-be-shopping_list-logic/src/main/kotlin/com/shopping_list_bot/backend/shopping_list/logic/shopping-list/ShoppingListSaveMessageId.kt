package com.shopping_list_bot.backend.shopping_list.logic.`shopping-list`

import com.shopping_list_bot.backend.shopping_list.logic.workers.saveMessageId
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListSaveMessageId : ICorExecutor<BeContextShoppingList> by chain({
    saveMessageId("Saving message id")
}).build()