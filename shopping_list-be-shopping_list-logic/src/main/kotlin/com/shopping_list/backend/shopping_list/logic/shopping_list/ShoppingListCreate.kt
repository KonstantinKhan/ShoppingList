package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain
import ru.fit_changes.cor.worker

object ShoppingListCreate : ICorExecutor<BeContext> by chain<BeContext>({
    chooseDb("Choose DB")
    repoReadState("")
    repoCreate("Creating repo")
    worker {
        handle {
            action = Action.UPDATE_PURCHASE_LIST
        }
    }
    sendCurrentShoppingList("")
    updateState("")
}).build()