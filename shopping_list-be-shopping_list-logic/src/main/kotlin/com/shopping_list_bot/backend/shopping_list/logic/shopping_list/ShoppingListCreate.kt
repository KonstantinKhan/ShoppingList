package com.shopping_list_bot.backend.shopping_list.logic.shopping_list

import com.shopping_list_bot.backend.shopping_list.logic.workers.repoCreate
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListCreate : ICorExecutor<BeContextShoppingList> by chain({
    repoCreate("Creating repo")
}).build()