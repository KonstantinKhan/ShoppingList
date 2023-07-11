package com.shopping_list.response

import com.shopping_list.common.models.MessageId

data class TgResponse(
    val result: Result = Result(MessageId.NONE)
)