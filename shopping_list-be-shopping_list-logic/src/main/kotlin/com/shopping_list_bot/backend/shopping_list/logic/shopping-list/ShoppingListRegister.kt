package com.shopping_list_bot.backend.shopping_list.logic.`shopping-list`

import com.shopping_list_bot.backend.shopping_list.logic.workers.*
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListRegister : ICorExecutor<BeContextShoppingList> by chain({
    chooseDb("Choose database")
    readStateContext("Read the context state")
    createShoppingList("Create shopping list")
    createStateContext("Create the context state")
    sendWelcomeMessage("Sending welcome message")
    updateStateContext("Update context in DB")
}).build()