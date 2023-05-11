package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.PurchaseModel
import com.shopping_list_bot.common.models.ShoppingListId
import com.shopping_list_bot.common.models.UserId
import com.shopping_list_bot.repo.IDbRequest

class DbPurchaseModelRequest(
    val shoppingListId: ShoppingListId,
    val purchase: PurchaseModel
) : IDbRequest