package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.CommonErrorModel
import com.shopping_list.common.models.shopping_list.ShoppingListId

data class DbShoppingListsIdsResponse(
    val shoppingLists: Collection<ShoppingListId>,
    val errors: Collection<CommonErrorModel> = emptyList()
)