package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain
import ru.fit_changes.cor.worker

object ShoppingListUpdate : ICorExecutor<BeContext> by chain<BeContext>({
    chooseDb("Choose DB")
    repoReadState("")
    worker {
        handle {
            action = Action.UPDATE_LIST
        }
    }
    searchLists("Search user lists")
    showListsForUpdate()
    updateState("")
}).build()