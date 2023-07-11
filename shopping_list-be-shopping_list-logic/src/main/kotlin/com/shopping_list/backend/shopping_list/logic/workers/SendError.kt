package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.CommonErrorModel
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.sendError() = worker {
    on { errors.contains(CommonErrorModel("Ваш список уже связан со списком пользователя"))}
    handle {
//        httpClient.sendError(this)
    }
}