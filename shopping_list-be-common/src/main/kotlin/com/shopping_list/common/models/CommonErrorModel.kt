package com.shopping_list.common.models

import com.shopping_list.common.IError

data class CommonErrorModel(
    override val message: String
) : IError