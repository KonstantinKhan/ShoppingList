package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain
import ru.fit_changes.cor.worker

object AddPurchase : ICorExecutor<BeContext> by chain<BeContext>({
    chooseDb("Choose DB")
    repoReadState("Read the context from the DB")
    repoReadShoppingList("Read from DB")

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