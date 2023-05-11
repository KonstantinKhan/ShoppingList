package com.shopping_list_bot.backend.shopping_list.logic.shopping_list

import com.shopping_list_bot.backend.shopping_list.logic.workers.*
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain

object AddPurchase : ICorExecutor<BeContextShoppingList> by chain<BeContextShoppingList>({
    chooseDb("Choose DB")
    readStateContext("Read the context from the DB")
    repoRead("Read from DB")

//    worker {
//        on {
//            dbShoppingList.user == TgUser.NONE
//        }
//        handle {
//            shoppingListRepo.createShoppingList(DbShoppingListRequest(ShoppingListModel(user = tgUser))).result?.let {
//                dbShoppingList = it
//            }
//        }
//    }

//    chain {
//        on {
//            dbShoppingList.isContainsUncheckedPurchase(purchase)
//        }
//        sendCurrentShoppingList("Send the current shopping list")
//        saveMessageId("Save the message id")
//    }

//    chain {
//        on {
//            dbShoppingList.isContainsCheckedPurchase(purchase)
//        }
//        checkPurchase("Check purchase")
//        repoUpdate("Update repo")
//        repoReadMessageId("Read the message id of user")
//        deletePreviousMessage("Delete the previous message")
//        sendCurrentShoppingList("Send the current shopping list")
//        saveMessageId("Save the message id")
//    }

    chain {
        on {
            !dbShoppingList.isContainsUncheckedPurchase(purchase)
        }
        addPurchase("Added a purchase to the shoppingList in context")
//        repoUpdate("Update shopping list in DB")
//        repoReadMessageId("Read the message id of user")
//        deletePreviousMessage("Delete the previous message")
//        sendCurrentShoppingList("Send the current shopping list")
//        saveMessageId("Save the message id")
    }
}).build()