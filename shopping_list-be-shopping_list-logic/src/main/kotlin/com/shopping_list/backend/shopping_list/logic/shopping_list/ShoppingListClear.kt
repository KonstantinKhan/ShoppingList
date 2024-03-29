package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListClear : ICorExecutor<BeContext> by chain({
    chooseDb("Choose DB")
    repoReadState("Read the context state")
    clearShoppingList("Clear the shopping list")
    repoReadShoppingList("Read the shopping list from DB")
    prepareShoppingList()
    sendCurrentShoppingList("Send the current shopping list")
    updateState("Update the state of context")
}).build()