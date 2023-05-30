package com.shopping_list_bot.backend.shopping_list.logic.`shopping-list`

import com.shopping_list_bot.backend.shopping_list.logic.workers.*
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShareShoppingList : ICorExecutor<BeContextShoppingList> by chain({
    chooseDb("Choose DB")
    readStateContext("Read the context from the DB")
    readShoppingListsOfUserConsumer("Read shopping lists of the user consumer")
    shareShoppingList("Shared the shopping list")
    sendCurrentShoppingList("Send current shopping list")
//    sendSharedShoppingList("Send shared shopping list")
}).build()