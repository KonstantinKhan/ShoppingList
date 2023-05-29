package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.shopping_list.PurchaseModel
import com.shopping_list_bot.common.models.shopping_list.ShoppingListId
import com.shopping_list_bot.repo.IDbRequest

class DbPurchaseModelRequest(
    val shoppingListId: ShoppingListId,
    val purchase: Collection<PurchaseModel>
) : IDbRequest