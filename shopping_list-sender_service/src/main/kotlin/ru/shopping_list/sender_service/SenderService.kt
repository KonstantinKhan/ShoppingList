package ru.shopping_list.sender_service

import com.shopping_list.ISender
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.MessageId
import com.shopping_list.lib.telegram.api.dsl.*
import com.shopping_list.response.Result
import com.shopping_list.response.TgResponse
import ru.ktglib.methods.Bot
import ru.ktglib.transport.models.KeyboardButtonRequestUser
import ru.ktglib.types.Message
import ru.shopping_list.sender_service.helpers.replaceExt

class SenderService(baseUrl: String) : ISender {
    private val bot = Bot(baseUrl)
    override suspend fun sendWelcomeMessage(context: BeContext): TgResponse {
        bot.sendMessage(message {
            chatId = context.shoppingList.user.userId.toLong()
            text = "Этот бот помогает создавать списки покупок и управлять ими.\n"
            replyMarkup = replyKeyboardMarkup {
                oneTimeKeyboard = true
                resizeKeyboard = true
                row {
                    button {
                        text = "Создать общий список"
                        requestUser = KeyboardButtonRequestUser(requestId = 52, userIsBot = false)
                    }
                }
            }
        })
        return TgResponse()
    }

    override suspend fun sendCurrentShoppingList(context: BeContext): TgResponse =
        bot.sendMessage(message {
            chatId = context.shoppingList.user.userId.toLong()
            text =
                if (context.action == Action.NONE) "Создан \uD83D\uDCDD _${context.shoppingList.title}_" +
                        ". \nОн пока пустой. Отправь сообщение, чтобы добавить запись.".replaceExt()
                else if (context.shoppingList.relatedLists.isNotEmpty()) "\uD83D\uDD17 " else "" +
                        if (context.shoppingList.purchaseList.isEmpty()) "\uD83D\uDCDD _${
                            context.shoppingList.title.toString().replaceExt()
                        }_" + (" пока пустой.\n" +
                                "Отправь сообщение, чтобы добавить запись.").replaceExt()
                        else
                            "\uD83D\uDCDD _${context.shoppingList.title.toString().replaceExt()}_: \n" +
                                    "-".repeat(context.shoppingList.title.toString().length * 3).replaceExt() + "\n" +
                                    context.shoppingList.purchaseList.joinToString("\n") {
                                        if (it.checked) "✅ ~${it.name.replaceExt()}~"
                                        else "\uD83D\uDD32 ${it.name}".replaceExt()
                                    }
            replyMarkup = inlineKeyboardMarkup {
                context.shoppingList.purchaseList.filter { !it.checked }.map {
                    row {
                        button {
                            text = it.name
                            callbackData = it.name
                        }
                    }
                }
            }
            parseMode = "MarkdownV2"
        }).result?.let {
            when (it) {
                is Message -> TgResponse(result = Result(messageId = MessageId(it.messageId)))
                else -> throw Error("Smart cast error")
            }
        } ?: TgResponse()

    override suspend fun editCurrentShoppingList(context: BeContext): TgResponse =
        bot.editMessageText(editMessageText {
            chatId = context.shoppingList.user.userId.toLong()
            messageId = context.messageId.toInt()
            text = if (context.shoppingList.relatedLists.isNotEmpty()) "\uD83D\uDD17 " else "" +
                    if (context.shoppingList.purchaseList.isEmpty()) "\uD83D\uDCDD _${
                        context.shoppingList.title.toString().replaceExt()
                    }_" + (" пока пустой.\n" +
                            "Отправь сообщение, чтобы добавить запись.").replaceExt()
                    else
                        "\uD83D\uDCDD _${context.shoppingList.title.toString().replaceExt()}_: \n" +
                                "-".repeat(context.shoppingList.title.toString().length * 3).replaceExt() + "\n" +
                                context.shoppingList.purchaseList.joinToString("\n") {
                                    if (it.checked) "✅ ~${it.name.replaceExt()}~"
                                    else "\uD83D\uDD32 ${it.name}".replaceExt()
                                }
            replyMarkup = inlineKeyboardMarkup {
                context.shoppingList.purchaseList.filter { !it.checked }.map {
                    row {
                        button {
                            text = it.name
                            callbackData = it.name
                        }
                    }
                }
            }
            parseMode = "MarkdownV2"
        }).result?.let {
            when (it) {
                is Message -> TgResponse(result = Result(messageId = MessageId(it.messageId)))
                else -> throw Error("Smart cast error")
            }
        } ?: TgResponse()

    override suspend fun showLists(context: BeContext): TgResponse =
        bot.sendMessage(message {
            chatId = context.shoppingList.user.userId.toLong()
            text =
//                (if (context.shoppingLists.first().id != context.shoppingList.id) "_${" ".repeat(6)} ${context.shoppingLists.first().title}" else "").toString()

                context.shoppingLists
                    .joinToString("\n", "") {
                        if (it.id == context.shoppingList.id) "\uD83D\uDC49 ${it.title}"
                        else "➖ ${it.title}"
                    }
            replyMarkup = inlineKeyboardMarkup {
                context.shoppingLists.map {
                    row {
                        button {
                            text =
                                "${if (it.relatedLists.isNotEmpty()) "\uD83D\uDD17" else ""} ${it.title}"
                            callbackData = "${it.id.asUUID()}"
                        }
                    }
                }
            }
        }).result?.let {
            when (it) {
                is Message -> TgResponse(result = Result(messageId = MessageId(it.messageId)))
                else -> throw Error("Smart cast error")
            }
        } ?: TgResponse()
}
