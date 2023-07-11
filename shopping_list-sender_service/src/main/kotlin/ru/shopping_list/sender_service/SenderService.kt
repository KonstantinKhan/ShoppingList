package ru.shopping_list.sender_service

import com.shopping_list.ISender
import com.shopping_list.common.context.BeContext
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
                if (context.shoppingList.purchaseList.isEmpty()) "Создан \uD83D\uDCDD _${context.shoppingList.title}_" +
                        ". \nОн пока пустой. Отправь сообщение, чтобы добавить запись.".replaceExt()
                else if (context.shoppingList.relatedLists.isNotEmpty()) "\uD83D\uDD17 " else "" +
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
                is Message -> {
                    TgResponse(
                        result = Result(messageId = MessageId(it.messageId))
                    )
                }

                else -> throw Error("Smart cast error")
            }
        } ?: TgResponse()


//    override suspend fun sendWelcomeMessage(context: BeContext): Response =
//        client.sendWelcomeMessage(context.shoppingList.user.userId)
//
//    override suspend fun sendCurrentShoppingList(context: BeContext): Response =
//        client.sendCurrentShoppingList(context)
//
//    override suspend fun sendError(context: BeContext): Response = client.sendError(context)
//
//    override suspend fun sendRecipientNotification(context: BeContext): Response =
//        client.sendRecipientNotification(context)
//
//    override suspend fun showLists(context: BeContext): Response = client.showLists(context)
//    override suspend fun sendListTitle(context: BeContext): Response = client.sendListTitle(context)
//    override suspend fun answerCallbackQuery(context: BeContext) = client.answerCallbackQuery(context)
//
//    override suspend fun deleteMessage(context: BeContext) =
//        client.deleteMessage(context.shoppingList.user.userId, context.messageId)
//
//    override suspend fun editMessage(context: BeContext): Response =
//        client.editMessage(context)
//
//    override suspend fun sendPreInviteMessage(context: BeContext): Response =
//        client.sendPreInviteMessage(context.shoppingList.user.userId)
//
//    override suspend fun sendInviteMessage(context: BeContext): Response =
//        client.sendInviteMessage(context.shoppingList.user.userId, context.bot.userName ?: "")
//
//    override suspend fun forwardMessage(context: BeContext): Response = client.forwardMessage(context)
//    override suspend fun getMe(): Response = client.getMe()
//
//    override suspend fun getChat(context: BeContext): Response = client.getChat(context)
}
