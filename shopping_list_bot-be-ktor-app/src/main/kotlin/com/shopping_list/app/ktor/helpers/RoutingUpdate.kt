package com.shopping_list.app.ktor.helpers

import com.shopping_list.app.ktor.AppKtorConfig
import com.shopping_list.backend.shopping_list.service.ShoppingListService
import ru.ktglib.transport.models.commands.Commands
import ru.ktglib.transport.models.update.Update
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery
import ru.ktglib.transport.models.update.UpdateWithMessage

suspend inline fun routingUpdate(
    updateRequest: Update,
    config: AppKtorConfig
) {
    val shoppingListService: ShoppingListService = config.shoppingListService

    with(updateRequest) {
        when (this) {
            is UpdateWithMessage -> {
                when (message.text) {
                    Commands.START.value -> {
                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
                            shoppingListService.register(this, it)
                        }
                    }

                    Commands.CLEAR.value -> {
                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
                            shoppingListService.clear(this, it)
                        }
                    }

                    Commands.TIDY.value -> {
                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
                            shoppingListService.tidy(this, it)
                        }
                    }

                    Commands.CHOOSE.value -> {
                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
                            shoppingListService.choose(this, it)
                        }
                    }

                    Commands.DETACH.value -> {
                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
                            shoppingListService.detach(this, it)
                        }
                    }

                    Commands.DELETE.value -> {
                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
                            shoppingListService.delete(this, it)
                        }
                    }

                    Commands.CREATE.value -> {
                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
                            shoppingListService.create(this, it)
                        }
                    }

                    Commands.UPDATE.value -> {
                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
                            shoppingListService.update(this, it)
                        }
                    }
//
//                    Commands.HELP.value -> {
//                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
//                            shoppingListService.help(this, it)
//                        }
//                    }
//                    Commands.RELATED.value -> {
//                        handleUpdate<UpdateWithMessage>(config, updateRequest) {
//                            shoppingListService.showRelatedShoppingLists(this, it)
//                        }
//                    }

                    else -> {
                        if (this.message.userShared != null || this.message.chatShared != null) {
                            handleUpdate<UpdateWithMessage>(config, updateRequest) {
                                shoppingListService.shareShoppingList(this, it)
                            }
                        } else
                            handleUpdate<UpdateWithMessage>(config, updateRequest) {
                                shoppingListService.handleMessage(this, it)
                            }
//                            handleUpdate<UpdateWithMessage>(config, updateRequest) {
//                                shoppingListService.addPurchase(this, it)
//                            }
//                        handleUpdate<UpdateWithMessage>(updateRequest) { update ->
//                            shoppingListService.addPurchase(this, update)
//                            if (messageId != MessageId.NONE) {
//                                httpClient.deleteMessage(userId, messageId)
//                            }
//
//                            val respondUpdate = httpClient.sendCurrentShoppingList(this)
//                            val json = Json {
//                                ignoreUnknownKeys = true
//                            }
//                            val messageId =
//                                MessageId(json.decodeFromString<Response>(respondUpdate.bodyAsText()).result.messageId)
//                            shoppingListService.saveMessageId(this, messageId)
//                        }
                    }
                }
            }
//
            is UpdateWithCallbackQuery -> {
                handleUpdate<UpdateWithCallbackQuery>(config, updateRequest) {
                    shoppingListService.handleCallbackData(this, it)
//                handleUpdate<UpdateWithCallbackQuery>(config, updateRequest) {
//                    shoppingListService.checkPurchase(this, it)
                }

//                httpClient.answerCallbackQuery(
//                    AnswerCallbackQueryRequest(
//                        callbackQueryId = callbackQuery.id,
//                        text = (updateRequest as UpdateWithCallbackQuery).callbackQuery.data,
////                        showAlert = false
//                    )
//                )
//                handleUpdate<UpdateWithCallbackQuery>(updateRequest) {
//                    shoppingListService.checkPurchase(this, it)
//                    httpClient.deleteMessage(userId, messageId)
//                    val respondUpdate = httpClient.sendCurrentShoppingList(this)
//                    val json = Json {
//                        ignoreUnknownKeys = true
//                    }
//                    val messageId = MessageId(json.decodeFromString<Response>(respondUpdate.bodyAsText()).result.messageId)
//                    shoppingListService.saveMessageId(this, messageId)
//                }
            }
        }
    }
}