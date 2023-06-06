package com.shopping_list.common.models

data class TgUser(
    val userId: UserId,
    val firstName: String,
    val lastName: String = "",
    val userName: String = ""
) {
    companion object {
        val NONE = TgUser(
            userId = UserId.NONE,
            firstName = "",
            lastName = "",
            userName = ""
        )
    }
}
