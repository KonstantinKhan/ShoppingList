package com.shopping_list_bot.common.context

import com.shopping_list_bot.common.models.MessageId
import com.shopping_list_bot.common.models.ShoppingListId
import com.shopping_list_bot.common.models.ShoppingListModel
import com.shopping_list_bot.common.models.TgUser
import com.shopping_list_bot.http.IHttpClient
import com.shopping_list_bot.repo.shopping_list.IRepoShoppingList

data class BeContextShoppingList(
    var config: ShoppingListContextConfig = ShoppingListContextConfig(),
    var shoppingListRepo: IRepoShoppingList = IRepoShoppingList.NONE,
    var httpClient: IHttpClient = IHttpClient.NONE,
    var dbShoppingList: ShoppingListModel = ShoppingListModel(),
    var messageId: MessageId = MessageId.NONE,
    var purchaseList: Collection<String> = emptyList(),
    var shoppingList: ShoppingListModel = ShoppingListModel(
        user = TgUser.NONE,
        purchaseList = ArrayList()
    ),
    var dependentShoppingLists: Collection<ShoppingListId> = emptyList(),
    var consumer: TgUser = TgUser.NONE,
    val shoppingListsOfUserConsumer: MutableList<ShoppingListId> = mutableListOf()
)

