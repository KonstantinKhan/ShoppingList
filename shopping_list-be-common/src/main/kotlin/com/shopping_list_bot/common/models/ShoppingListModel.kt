package com.shopping_list_bot.common.models

data class ShoppingListModel(
    val id: ShoppingListId = ShoppingListId.NONE,
    val name: String = "Новый список",
    val user: TgUser = TgUser.NONE,
    val purchaseList: Collection<PurchaseModel> = emptyList(),
) {
    fun isContainsUncheckedPurchase(purchase: String) = purchaseList
        .filter { !it.checked }
        .map { it.name }
        .contains(purchase)

    fun isContainsCheckedPurchase(purchase: String) = purchaseList
        .filter { it.checked }
        .map { it.name }
        .contains(purchase)
}

