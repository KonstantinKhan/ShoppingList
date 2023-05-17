package com.shopping_list_bot.common.models

data class ShoppingListModel(
    val id: ShoppingListId = ShoppingListId.NONE,
    val name: String = "Новый список",
    val user: TgUser = TgUser.NONE,
    val purchaseList: Collection<PurchaseModel> = emptyList(),
) {
    fun isContainsUncheckedPurchase(purchaseList: Collection<String>) =
        this.purchaseList.filter { !it.checked }.map { it.name }
            .intersect(purchaseList.toSet()).isNotEmpty()

    fun isContainsCheckedPurchase(purchaseList: Collection<String>) =
        this.purchaseList.filter { it.checked }.map { it.name }
            .intersect(purchaseList.toSet()).isNotEmpty()
}

