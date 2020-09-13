package jp.co.cyberagent.dojo2020.data.remote.firestore.category

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.cyberagent.dojo2020.data.model.Category
import jp.co.cyberagent.dojo2020.data.remote.firestore.FireStoreConstants
import jp.co.cyberagent.dojo2020.data.remote.firestore.categoriesRef
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

interface FirestoreCategoryDataSource {
    suspend fun saveCategory(uid: String, category: Category)

    fun fetchAllCategory(uid: String): Flow<List<Category>>

    suspend fun deleteCategory(uid: String, category: Category)
}

class DefaultFirestoreCategoryDataSource(private val firestore: FirebaseFirestore) :
    FirestoreCategoryDataSource {

    override suspend fun saveCategory(uid: String, category: Category) {
        firestore.categoriesRef(uid).document().set(category.toEntity())
    }

    override fun fetchAllCategory(uid: String) = flow<List<Category>> {
        try {
            val snapshot = firestore.categoriesRef(uid).get().await()

            val categoryEntityList = snapshot.toObjects(CategoryEntity::class.java)

            emit(categoryEntityList.mapNotNull { it.modelOrNull() })
        } catch (e: Exception) {
            //Todo(emit failed)
        }
    }

    override suspend fun deleteCategory(uid: String, category: Category) {
        val snapshot = firestore.categoriesRef(uid)
            .whereEqualTo(FireStoreConstants.CATEGORY, category)
            .get()
            .await()

        snapshot.forEach {
            it.reference.delete()
        }
    }

    private fun Category.toEntity(): CategoryEntity {
        return CategoryEntity(name)
    }

}