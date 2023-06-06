package com.shopping_list.common.models.shopping_list

import com.shopping_list.common.models.TgUser

data class ShoppingListModel(
    val id: ShoppingListId = ShoppingListId.NONE,
    val title: ShoppingListTitle = ShoppingListTitle.NONE,
    val user: TgUser = TgUser.NONE,
    val purchaseList: Collection<PurchaseModel> = emptyList(),
    val prototypeShoppingLists: Collection<ShoppingListId> = emptyList(),
    val derivativeShoppingList: Collection<ShoppingListId> = emptyList()
) {
    fun isContainsUncheckedPurchase(purchaseList: Collection<String>) =
        this.purchaseList.filter { !it.checked }.map { it.name }
            .intersect(purchaseList.toSet()).isNotEmpty()

    fun isContainsCheckedPurchase(purchaseList: Collection<String>) =
        this.purchaseList.filter { it.checked }.map { it.name }
            .intersect(purchaseList.toSet()).isNotEmpty()
}

