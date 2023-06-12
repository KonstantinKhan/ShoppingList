package com.shopping_list.repo.shopping_list

import com.shopping_list.common.IError
import com.shopping_list.common.models.shopping_list.ShoppingListModel

data class DbShoppingListsResponse(
    val shoppingLists: Collection<ShoppingListModel> = emptyList(),
    val errors: Collection<IError> = emptyList()
)
