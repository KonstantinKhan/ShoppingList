package com.shopping_list_bot.common.models.`shopping-list`

import com.shopping_list_bot.common.models.TgUser

data class ShoppingListModel(
    val id: ShoppingListId = ShoppingListId.NONE,
    val title: ShoppingListTitle = ShoppingListTitle.NONE,
    val user: TgUser = TgUser.NONE,
    val purchaseList: Collection<PurchaseModel> = emptyList(),
    val prototypeShoppingLists: Collection<ShoppingListModel> = emptyList(),
    val derivativeShoppingList: Collection<ShoppingListModel> = emptyList()
) {
    fun isContainsUncheckedPurchase(purchaseList: Collection<String>) =
        this.purchaseList.filter { !it.checked }.map { it.name }
            .intersect(purchaseList.toSet()).isNotEmpty()

    fun isContainsCheckedPurchase(purchaseList: Collection<String>) =
        this.purchaseList.filter { it.checked }.map { it.name }
            .intersect(purchaseList.toSet()).isNotEmpty()
}

