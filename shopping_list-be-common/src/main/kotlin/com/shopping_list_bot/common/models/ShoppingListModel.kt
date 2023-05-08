package com.shopping_list_bot.common.models

import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

data class ShoppingListModel(
    val id: ShoppingListId = ShoppingListId.NONE,
    val name: String = "Новый список",
    val user: TgUser = TgUser.NONE,
    val purchaseList: Collection<PurchaseModel> = emptyList(),
    val date: Instant = Instant.now()
) {
    fun printShortDateShoppingList(): String =
        SimpleDateFormat("dd MMMM", Locale("ru")).format(Date.from(date))

    fun isContainsUncheckedPurchase(purchase: String) = purchaseList
        .filter { !it.checked }
        .map { it.name }
        .contains(purchase)

    fun isContainsCheckedPurchase(purchase: String) = purchaseList
        .filter { it.checked }
        .map { it.name }
        .contains(purchase)
}

