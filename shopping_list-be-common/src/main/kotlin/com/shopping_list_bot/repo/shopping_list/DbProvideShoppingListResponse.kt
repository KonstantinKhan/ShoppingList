package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.ShoppingListModel

class DbProvideShoppingListResponse(
    val parentShoppingList: ShoppingListModel,
    val childShoppingList: ShoppingListModel
)