package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.MessageId
import com.shopping_list.repo.IDbResponse

data class DbMessageIdResponse(
    override val result: MessageId?
) : IDbResponse<MessageId>