package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.MessageId
import com.shopping_list_bot.repo.IDbResponse

data class DbMessageIdResponse(
    override val result: MessageId?
) : IDbResponse<MessageId>