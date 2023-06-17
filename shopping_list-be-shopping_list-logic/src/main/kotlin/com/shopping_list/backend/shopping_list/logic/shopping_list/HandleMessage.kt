package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.chooseDb
import com.shopping_list.backend.shopping_list.logic.workers.repoReadState
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object HandleMessage : ICorExecutor<BeContext> by chain({
    chooseDb("Choose DB")
    repoReadState("Read the context from the DB")

}).build()