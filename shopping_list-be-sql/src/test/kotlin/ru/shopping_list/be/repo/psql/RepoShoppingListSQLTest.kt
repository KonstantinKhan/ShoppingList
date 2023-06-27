package ru.shopping_list.be.repo.psql

import com.shopping_list.repo.shopping_list.IRepoShoppingList
import ru.shopping_list.be.repo.test.RepoShoppingListUpdateTest

class RepoShoppingListSQLTest: RepoShoppingListUpdateTest() {
    override val repo: IRepoShoppingList = SQLTestCompanion.repoUnderTestContainer(initObjects)
}
