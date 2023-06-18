package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShoppingListTidy : ICorExecutor<BeContext> by chain({
    chooseDb("Choose DB")
    repoReadState("Read the context state")
    deleteChecked("Delete checked items")
    sendCurrentShoppingList("Send the current shopping list")
    updateState("Update the state of context")

//    repoRead("Read from DB")
//    prepareForTidy("Prepare the shopping list")
//    repoUpdate("Update shopping list in DB")
//    repoReadMessageId("Read the message id of user")
//    deletePreviousMessage("Delete the previous message")
//    sendCurrentShoppingList("Send the current shopping list")
//    saveMessageId("Save the message id")
}).build()