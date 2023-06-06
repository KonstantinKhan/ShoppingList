package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.UserId
import com.shopping_list.repo.IDbRequest

class DbUserIdRequest(
    val userId: UserId,
): IDbRequest