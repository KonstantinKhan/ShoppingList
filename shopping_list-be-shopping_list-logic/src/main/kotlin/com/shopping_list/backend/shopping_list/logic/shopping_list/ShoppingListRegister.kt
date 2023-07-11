package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListRegister : ICorExecutor<BeContext> by chain({
    chooseDb("Choose database")
    repoReadState("Read the context state")
    repoReadShoppingList("")
    prepareShoppingList()
    createShoppingList("Create shopping list")
    createState("Create the context state")
    sendWelcomeMessage("Sending welcome message")
    sendCurrentShoppingList("")
    updateState("Update context in DB")
}).build()