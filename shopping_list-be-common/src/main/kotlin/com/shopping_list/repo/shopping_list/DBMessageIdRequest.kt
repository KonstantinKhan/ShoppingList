package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.UserId
import com.shopping_list.repo.IDbRequest

class DBMessageIdRequest(
    val userId: UserId,
    val messageId: MessageId = MessageId.NONE
): IDbRequest