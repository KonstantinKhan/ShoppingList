package com.shopping_list.backend.shopping_list.logic.shopping_list

import com.shopping_list.backend.shopping_list.logic.workers.*
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.repo.shopping_list.DbShoppingListRequest
import ru.fit_changes.cor.ICorExecutor
import ru.fit_changes.cor.chain
import ru.fit_changes.cor.worker

object HandleCallbackData : ICorExecutor<BeContext> by chain<BeContext>({
    chooseDb("Choose DB")
    repoReadState("Read the state from the DB")
    chain {
        on { action == Action.UPDATE_PURCHASE_LIST }
        repoReadShoppingList("Read from db")
        checkPurchase("Check purchase")
        repoReadShoppingList("")
        prepareShoppingList()
        editMessage("Edit the message")
    }
    chain {
        on { action == Action.CHOOSE_LIST }
        worker {
            handle {
                shoppingList = shoppingList.copy(id = ShoppingListId(messageText))
            }
        }
        repoReadShoppingList("")
        prepareShoppingList()
        sendCurrentShoppingList("")
        worker {
            handle {
                action = Action.UPDATE_PURCHASE_LIST
            }
        }
        updateState("")
    }

    chain {
        on { action == Action.DETACH_LIST }
        worker {
            handle {
                shoppingList = shoppingList.copy(id = ShoppingListId(messageText))
            }
        }
        detachList()
        worker {
            handle {
                action = Action.CHOOSE_LIST
            }
        }
        searchLists("Search user lists")
        showLists()
        updateState("")
    }

    chain {
        on { action == Action.DELETE_LIST }
        deleteList()
        worker {
            handle {
                action = Action.CHOOSE_LIST
            }
        }
        searchLists("Search user lists")
        chain {
            on { shoppingLists.isEmpty() }
            worker {
                handle {
                    shoppingListRepo.createShoppingList(
                        DbShoppingListRequest(
                            shoppingList = ShoppingListModel(
                                user = shoppingList.user,
                            )
                        )
                    )
                }
            }
            searchLists("Search user lists")
        }
        showLists()
        updateState("")
    }

    chain {
        on { action == Action.UPDATE_LIST }
        worker {
            handle {
                shoppingList = shoppingList.copy(id = ShoppingListId(messageText))
            }
        }
        repoReadShoppingList("")
        prepareShoppingList()
        sendListTitle()
        updateState("")
    }
}).build()