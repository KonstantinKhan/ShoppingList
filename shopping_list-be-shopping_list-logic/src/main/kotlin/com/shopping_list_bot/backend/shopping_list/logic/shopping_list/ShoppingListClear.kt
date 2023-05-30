package com.shopping_list_bot.backend.shopping_list.logic.shopping_list

import com.shopping_list_bot.backend.shopping_list.logic.workers.*
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListClear : ICorExecutor<BeContextShoppingList> by chain({
    chooseDb("Choose DB")
    readStateContext("Read the context state")
    clearShoppingList("Clear the shopping list")
    sendCurrentShoppingList("Send the current shopping list")
    updateStateContext("Update the state of context")
}).build()