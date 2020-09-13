package jp.co.cyberagent.dojo2020.data.local

import jp.co.cyberagent.dojo2020.data.local.db.ApplicationDataBase
import jp.co.cyberagent.dojo2020.data.local.db.category.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CategoryDataSource {
    suspend fun addCategory(category: String)

    fun fetchCategory(): Flow<List<String>>

    suspend fun deleteCategory(name: String)
}

class DefaultCategoryDataSource(private val database: ApplicationDataBase) : CategoryDataSource {
    override suspend fun addCategory(category: String) {
        database.categoryDao().insert(CategoryEntity(category))
    }

    override fun fetchCategory(): Flow<List<String>> {
        return database.categoryDao().fetchAll().map { categoryList ->
            categoryList.map { it.name }
        }
    }

    override suspend fun deleteCategory(name: String) {
        database.categoryDao().delete(name)
    }
}