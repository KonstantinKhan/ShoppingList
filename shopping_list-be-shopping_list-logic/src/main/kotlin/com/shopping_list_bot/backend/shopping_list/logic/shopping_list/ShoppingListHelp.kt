package com.shopping_list_bot.backend.shopping_list.logic.shopping_list

import com.shopping_list_bot.backend.shopping_list.logic.workers.chooseDb
import com.shopping_list_bot.backend.shopping_list.logic.workers.readStateContext
import com.shopping_list_bot.backend.shopping_list.logic.workers.sendWelcomeMessage
import com.shopping_list_bot.backend.shopping_list.logic.workers.updateStateContext
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListHelp : ICorExecutor<BeContextShoppingList> by chain({
    chooseDb("Choose database")
    readStateContext("Read the context state")
    sendWelcomeMessage("Sending welcome message")
    updateStateContext("Update context in DB")
}).build()