package com.shopping_list_bot.backend.shopping_list.logic.`shopping-list`

import com.shopping_list_bot.backend.shopping_list.logic.workers.*
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListCheckPurchase : ICorExecutor<BeContextShoppingList> by chain({
    chooseDb("Choose DB")
    readStateContext("Read the context from the DB")
    repoRead("Read from db")
    checkPurchase("Check purchase")
//    repoUpdate("Update repo")
//    repoReadMessageId("Read the message id of user")
    editMessage("Edit the message")
}).build()