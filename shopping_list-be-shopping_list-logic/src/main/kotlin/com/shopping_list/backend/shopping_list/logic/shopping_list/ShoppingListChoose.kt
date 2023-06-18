package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.chooseDb
import com.shopping_list.backend.shopping_list.logic.workers.searchLists
import com.shopping_list.backend.shopping_list.logic.workers.showLists
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListChoose : ICorExecutor<BeContext> by chain({
    chooseDb("Choose DB")
    searchLists("Search user lists")
    showLists()
}).build()