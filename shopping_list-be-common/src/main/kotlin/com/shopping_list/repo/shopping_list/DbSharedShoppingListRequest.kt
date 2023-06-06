package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.TgUser

class DbSharedShoppingListRequest(
    val sourceShoppingList: ShoppingListId,
    val userConsumer: TgUser,
    val shoppingListsOfUserConsumer: Collection<ShoppingListId>
)