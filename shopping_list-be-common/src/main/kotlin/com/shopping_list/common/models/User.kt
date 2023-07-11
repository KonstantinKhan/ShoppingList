package com.shopping_list.common.models

data class User(
    val userId: UserId,
    val firstName: String,
    val lastName: String = "",
    val userName: String = ""
) {
    companion object {
        val NONE = User(
            userId = UserId.NONE,
            firstName = "",
            lastName = "",
            userName = ""
        )
    }
}
