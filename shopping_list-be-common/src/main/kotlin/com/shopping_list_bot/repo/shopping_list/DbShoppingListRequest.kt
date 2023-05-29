package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.shopping_list.ShoppingListModel
import com.shopping_list_bot.repo.IDbRequest

class DbShoppingListRequest(
    val shoppingList: ShoppingListModel,
): IDbRequest