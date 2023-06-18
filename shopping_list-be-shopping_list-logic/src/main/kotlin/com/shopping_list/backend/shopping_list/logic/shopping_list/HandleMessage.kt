package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object HandleMessage : ICorExecutor<BeContext> by chain<BeContext>({
    chooseDb("Choose DB")
    repoReadState("Read the state from the DB")
    repoReadShoppingList("Read the shopping list from DB")
    chain {
        on { action == Action.ADD_PURCHASE }
        chain {
            on { dbShoppingList.isContainsCheckedPurchase(messageText.lines()) }
            checkPurchase("Check purchase")
            repoReadShoppingList("")
            prepareShoppingList()
            sendCurrentShoppingList("Send the current shopping list")
            updateState("Update the state of context")
        }

        chain {

            on {
                dbShoppingList.isContainsUncheckedPurchase(messageText.lines())
            }
            prepareShoppingList()
        }

        chain {
            on {
                !dbShoppingList.isContainsCheckedPurchase(messageText.lines())
                        && !dbShoppingList.isContainsUncheckedPurchase(messageText.lines())
            }
            addPurchase("Added a purchase to the shoppingList in context")
            prepareShoppingList()
            sendCurrentShoppingList("Send the current shopping list")
            updateState("Update the state of context")
        }
    }
}).build()