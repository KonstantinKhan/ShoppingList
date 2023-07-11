package com.shopping_list.app.ktor.helpers

import com.shopping_list.app.ktor.AppKtorConfig
import com.shopping_list.common.context.BeContext
import ru.ktglib.transport.models.update.Update

inline fun <reified T : Update> handleUpdate(
    config: AppKtorConfig,
    update: Update,
    block: BeContext.(T) -> Unit
) {
    val context = BeContext(
        sender = config.sender,
    )
    context.block(update as T)
}