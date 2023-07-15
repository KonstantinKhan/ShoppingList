package com.shopping_list.response

import com.shopping_list.common.IError
import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.User

data class TgResponse(
    val result: Result = Result(MessageId.NONE),
    val user: User = User.NONE,
    val error: IError = IError.NONE
)