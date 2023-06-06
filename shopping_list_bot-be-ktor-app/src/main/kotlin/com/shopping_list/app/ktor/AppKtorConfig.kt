package com.shopping_list.app.ktor

import com.shopping_list.IHttpClient
import com.shopping_list.backend.shopping_list.logic.ShoppingListProcessor
import com.shopping_list.backend.shopping_list.service.ShoppingListService
import com.shopping_list.common.context.ShoppingListContextConfig
import io.ktor.server.application.*
import ru.shopping_list.be.repo.psql.RepoShoppingListPSQL
import ru.shopping_list.sender_service.SenderService

data class AppKtorConfig(
    val contextConfig: ShoppingListContextConfig =
        ShoppingListContextConfig(
            repoShoppingListInMemory = RepoShoppingListPSQL()
        ),
    val httpClient: IHttpClient = IHttpClient.NONE,
    val shoppingListCrud: ShoppingListProcessor = ShoppingListProcessor(contextConfig),
    val shoppingListService: ShoppingListService = ShoppingListService(shoppingListCrud),
) {
    internal constructor(environment: ApplicationEnvironment) : this(
        httpClient = SenderService(environment.config.property("ktor.deployment.url").getString())
    )
}
