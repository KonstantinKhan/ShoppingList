package com.shopping_list_bot.backend.shopping_list.logic.`shopping-list`

import com.shopping_list_bot.backend.shopping_list.logic.workers.*
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain
import ru.fit_changes.cor.worker

object AddPurchase : ICorExecutor<BeContextShoppingList> by chain<BeContextShoppingList>({
    chooseDb("Choose DB")
    readStateContext("Read the context from the DB")
    repoRead("Read from DB")
    readSharedData("Read shared data")

    chain {

        on {
            dbShoppingList.isContainsCheckedPurchase(purchaseList)
        }
        checkPurchase("Check purchase")
        sendCurrentShoppingList("Send the current shopping list")
        updateStateContext("Update the state of context")
    }

    chain {

        on {
            dbShoppingList.isContainsUncheckedPurchase(purchaseList)
        }
        worker {
            handle {
                shoppingList = dbShoppingList
            }
        }
        sendCurrentShoppingList("Send the current shopping list")
        updateStateContext("Update the state of context")
    }

    chain {
        on {
            !dbShoppingList.isContainsCheckedPurchase(purchaseList)
                    && !dbShoppingList.isContainsUncheckedPurchase(purchaseList)
        }
        addPurchase("Added a purchase to the shoppingList in context")
        sendCurrentShoppingList("Send the current shopping list")
        updateStateContext("Update the state of context")
    }

}).build()