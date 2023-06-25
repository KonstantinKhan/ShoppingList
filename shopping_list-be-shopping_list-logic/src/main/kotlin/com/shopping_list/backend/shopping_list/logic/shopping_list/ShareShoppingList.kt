package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object ShareShoppingList : ICorExecutor<BeContext> by chain({
    chooseDb("Choose DB")
    repoReadState("Read the context from the DB")
    checkChat("Check if it is possible to send a message to the user")
    getBotInfo("Get bot info")
    sendPreInviteMessage("Send the pre invite message")
    sendInviteMessage("Send the invite message")
    readShoppingListsOfRecipient("Read shopping lists of the user consumer")
    shareShoppingList("Shared the shopping list")
    sendCurrentShoppingList("Send current shopping list")
    sendSharedShoppingList("Send shared shopping list")
    sendError()
}).build()