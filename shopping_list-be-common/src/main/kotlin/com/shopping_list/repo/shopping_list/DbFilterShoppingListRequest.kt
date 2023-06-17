package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.UserId

data class DbFilterShoppingListRequest(
    val userId: UserId = UserId.NONE
)
