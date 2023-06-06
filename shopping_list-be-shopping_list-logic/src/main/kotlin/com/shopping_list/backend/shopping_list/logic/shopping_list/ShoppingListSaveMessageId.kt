package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.saveMessageId
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListSaveMessageId : ICorExecutor<BeContext> by chain({
    saveMessageId("Saving message id")
}).build()