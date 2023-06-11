package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.CommonErrorModel
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.repo.IDbResponse

data class DbShoppingListResponse(
    override val result: ShoppingListModel = ShoppingListModel(),
    override val error: CommonErrorModel? = null
) : IDbResponse<ShoppingListModel>
