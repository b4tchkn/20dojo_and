package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.local.CategoryDataSource
import jp.co.cyberagent.dojo2020.data.model.Category
import jp.co.cyberagent.dojo2020.data.remote.firestore.category.FirestoreCategoryDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

interface CategoryRepository {
    suspend fun saveCategory(uid: String?, category: Category)

    fun fetchAllCategory(uid: String?): Flow<List<Category>>

    suspend fun deleteCategory(uid: String?, category: Category)
}

class DefaultCategoryRepository(
    private val localCategoryDataSource: CategoryDataSource,
    private val remoteCategoryFirestoreDataSource: FirestoreCategoryDataSource
) : CategoryRepository {

    override suspend fun saveCategory(uid: String?, category: Category) {
        localCategoryDataSource.addCategory(category)
        uid ?: return

        remoteCategoryFirestoreDataSource.saveCategory(uid, category)
    }

    @FlowPreview
    override fun fetchAllCategory(uid: String?): Flow<List<Category>> {
        val localCategoryListFlow = localCategoryDataSource.fetchCategory()

        val categoryListFlow = localCategoryListFlow.flatMapConcat { categoryList ->
            if (uid != null && categoryList.isEmpty()) {
                return@flatMapConcat remoteCategoryFirestoreDataSource.fetchAllCategory(uid)
            }

            return@flatMapConcat localCategoryListFlow
        }

        return categoryListFlow
    }

    override suspend fun deleteCategory(uid: String?, category: Category) {
        localCategoryDataSource.deleteCategory(category)
        uid ?: return

        remoteCategoryFirestoreDataSource.deleteCategory(uid, category)
    }
}