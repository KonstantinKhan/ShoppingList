package com.shopping_list_bot.backend.shopping_list.logic.shopping_list

import com.shopping_list_bot.backend.shopping_list.logic.workers.chooseDb
import com.shopping_list_bot.backend.shopping_list.logic.workers.readRelatedShoppingList
import com.shopping_list_bot.backend.shopping_list.logic.workers.readStateContext
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShowRelatedShoppingLists : ICorExecutor<BeContextShoppingList> by chain({
    chooseDb("Choose DB")
    readStateContext("Read the context from the DB")
    readRelatedShoppingList("Read related shopping list")
}).build()