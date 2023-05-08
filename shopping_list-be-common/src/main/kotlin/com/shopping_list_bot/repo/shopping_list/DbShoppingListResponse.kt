package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.ShoppingListModel
import com.shopping_list_bot.repo.IDbResponse

data class DbShoppingListResponse(
    override val result: ShoppingListModel?
) : IDbResponse<ShoppingListModel>
