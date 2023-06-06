package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListCheckPurchase : ICorExecutor<BeContext> by chain({
    chooseDb("Choose DB")
    repoReadState("Read the context from the DB")
    repoReadShoppingList("Read from db")
    checkPurchase("Check purchase")
//    repoUpdate("Update repo")
//    repoReadMessageId("Read the message id of user")
    editMessage("Edit the message")
}).build()