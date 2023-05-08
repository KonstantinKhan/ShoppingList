package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.UserId
import com.shopping_list_bot.repo.IDbRequest

class DbUserIdRequest(
    val userId: UserId,
): IDbRequest