package com.shopping_list.repo

import com.shopping_list.common.models.CommonErrorModel

interface IDbResponse<T> {
    val result: T?
    val error: CommonErrorModel?
}
