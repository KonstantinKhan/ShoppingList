package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.shopping_list.ShoppingListModel
import com.shopping_list_bot.repo.IDbResponse

data class DbShoppingListResponse(
    override val result: ShoppingListModel = ShoppingListModel()
) : IDbResponse<ShoppingListModel>
