package com.shopping_list.backend.mapping

import com.shopping_list.common.context.BeContext
import ru.ktglib.types.additional.ChatId

fun BeContext.toChatId() = ChatId(recipient.userId.toLong())