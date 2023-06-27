package ru.shopping_list.be.repo.test

import com.shopping_list.common.models.State
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel

internal interface IInitObjects {
    val initShoppingListModels: List<ShoppingListModel>
    val initStates: Collection<State>
    val initSharedData: Collection<Pair<ShoppingListId, ShoppingListId>>
}