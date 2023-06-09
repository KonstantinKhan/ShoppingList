package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.shopping_list.ShoppingListTitle
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain
import ru.fit_changes.cor.worker

object HandleMessage : ICorExecutor<BeContext> by chain<BeContext>({
    chooseDb("Choose DB")
    repoReadState("Read the state from the DB")
    repoReadShoppingList("Read the shopping list from DB")
    chain {
        on { action == Action.UPDATE_PURCHASE_LIST }
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
        searchLists("")
        showLists()
    }
    chain {
        on { action == Action.UPDATE_LIST }
        worker {
            handle {
                shoppingList = dbShoppingList.copy(title = ShoppingListTitle(messageText))
            }
        }
        repoUpdate("")
        worker {
            handle {
                action = Action.CHOOSE_LIST
            }
        }
        searchLists("Search user lists")
        showLists()
        updateState("")
    }

}).build()