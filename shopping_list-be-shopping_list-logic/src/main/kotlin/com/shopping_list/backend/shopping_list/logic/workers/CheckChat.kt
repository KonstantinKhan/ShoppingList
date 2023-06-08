package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.CommonErrorModel
import com.shopping_list.response.ResponseWithError
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.checkChat(title: String) = worker {
    this.title = title
    handle {
        val result = httpClient.getChat(this)
        if (result is ResponseWithError)
            this.errors.add(CommonErrorModel(result.description))
        println("result in check chat: $result")
    }
}