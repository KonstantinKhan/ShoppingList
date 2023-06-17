package com.shopping_list.common.models.shopping_list

data class PurchaseModel(
    val name: String,
    val checked: Boolean = false
) {
    fun isNamesMatch(name: String) = this.name == name

    companion object {
        val NONE = PurchaseModel(
            ""
        )
    }
}
