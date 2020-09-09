package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.local.CategoryDataSource
import jp.co.cyberagent.dojo2020.data.remote.firestore.category.FirestoreCategoryDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

interface CategoryRepository {
    suspend fun saveCategory(uid: String?, category: String)

    suspend fun fetchAllCategory(uid: String?): Flow<List<String>>

    suspend fun deleteCategory(uid: String?, category: String)
}

class DefaultCategoryRepository(
    private val localCategoryDataSource: CategoryDataSource,
    private val remoteCategoryFirestoreDataSource: FirestoreCategoryDataSource
) : CategoryRepository {

    override suspend fun saveCategory(uid: String?, category: String) {
        localCategoryDataSource.addCategory(category)
        uid ?: return

        remoteCategoryFirestoreDataSource.saveCategory(uid, category)
    }

    @FlowPreview
    override suspend fun fetchAllCategory(uid: String?): Flow<List<String>> {
        val localCategoryListFlow = localCategoryDataSource.fetchCategory()

        val categoryListFlow = localCategoryListFlow.flatMapConcat { categoryList ->
            if (uid != null && categoryList.isEmpty()) {
                return@flatMapConcat remoteCategoryFirestoreDataSource.fetchAllCategory(uid)
            }

            return@flatMapConcat localCategoryListFlow
        }

        return categoryListFlow
    }

    override suspend fun deleteCategory(uid: String?, category: String) {
        localCategoryDataSource.deleteCategory(category)
        uid ?: return

        remoteCategoryFirestoreDataSource.deleteCategory(uid, category)
    }
}