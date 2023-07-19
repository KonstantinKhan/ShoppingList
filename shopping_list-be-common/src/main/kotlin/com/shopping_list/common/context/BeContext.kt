package com.shopping_list.common.context

import com.shopping_list.ISender
import com.shopping_list.common.IError
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.common.models.User
import com.shopping_list.repo.shopping_list.IRepoShoppingList

data class BeContext(
    var config: ShoppingListContextConfig = ShoppingListContextConfig(),
    var shoppingListRepo: IRepoShoppingList = IRepoShoppingList.NONE,
    var sender: ISender = ISender.NONE,
    var dbShoppingList: ShoppingListModel = ShoppingListModel(),
    var messageId: MessageId = MessageId.NONE,
    var purchaseList: Collection<String> = emptyList(),
    var shoppingList: ShoppingListModel = ShoppingListModel(),
    val shoppingLists: MutableList<ShoppingListModel> = mutableListOf(),
    var dependentShoppingLists: Collection<ShoppingListId> = emptyList(),
    var recipient: User = User.NONE,
    val shoppingListsOfRecipient: MutableList<ShoppingListId> = mutableListOf(),
    val errors: MutableList<IError> = mutableListOf(),
    var bot: User = User.NONE,
    var duplicateShoppingList: ShoppingListModel = ShoppingListModel(),
    var messageText: String = "",
    var action: Action = Action.NONE,
    var queryId: String = ""
)

