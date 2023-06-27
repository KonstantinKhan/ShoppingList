package ru.shopping_list.backend.shopping_list.logic

import com.shopping_list.common.models.State
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import org.testcontainers.containers.PostgreSQLContainer
import ru.shopping_list.be.repo.psql.RepoShoppingListPSQL
import java.time.Duration

class PostgresContainer : PostgreSQLContainer<PostgresContainer>("postgres:15.2")

object SQLTestCompanion {
    private const val USER = "postgres"
    private const val PASSWORD = "admin"

    private val container by lazy {
        PostgresContainer().apply {
            withUsername(USER)
            withPassword(PASSWORD)
            withDatabaseName("shoppinglistdb")
            withStartupTimeout(Duration.ofSeconds(300L))
            start()
        }
    }

    private val url: String by lazy { container.jdbcUrl }

    fun repoUnderTestContainer(
        initShoppingLists: Collection<ShoppingListModel>,
        initStates: Collection<State>,
        initSharedData: Collection<Pair<ShoppingListId, ShoppingListId>>
    ): RepoShoppingListPSQL {
        return RepoShoppingListPSQL(
            url,
            user = USER,
            password = PASSWORD,
            initShoppingLists = initShoppingLists,
            initStates = initStates,
            initSharedData = initSharedData,
        )
    }
}