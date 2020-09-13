package jp.co.cyberagent.dojo2020.data.local

import jp.co.cyberagent.dojo2020.data.local.db.ApplicationDataBase
import jp.co.cyberagent.dojo2020.data.local.db.category.CategoryEntity
import jp.co.cyberagent.dojo2020.data.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CategoryDataSource {
    suspend fun addCategory(category: Category)

    fun fetchCategory(): Flow<List<Category>>

    suspend fun deleteCategory(name: Category)
}

class DefaultCategoryDataSource(private val database: ApplicationDataBase) : CategoryDataSource {
    override suspend fun addCategory(category: Category) {
        database.categoryDao().insert(category.toEntity())
    }

    override fun fetchCategory(): Flow<List<Category>> {
        return database.categoryDao().fetchAll().map { categoryList ->
            categoryList.map { it.toModel() }
        }
    }

    override suspend fun deleteCategory(category: Category) {
        database.categoryDao().delete(category.name)
    }

    private fun Category.toEntity(): CategoryEntity {
        return CategoryEntity(name)
    }
}