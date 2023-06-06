package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.shopping_list.ShoppingListModel

class DbProvideShoppingListResponse(
    val parentShoppingList: ShoppingListModel,
    val childShoppingList: ShoppingListModel
)