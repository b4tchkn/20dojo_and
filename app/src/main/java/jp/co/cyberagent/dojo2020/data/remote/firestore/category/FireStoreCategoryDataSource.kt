package jp.co.cyberagent.dojo2020.data.remote.firestore.category

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.cyberagent.dojo2020.data.model.Category
import jp.co.cyberagent.dojo2020.data.remote.firestore.FireStoreConstants
import jp.co.cyberagent.dojo2020.data.remote.firestore.categoriesRef
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

interface FirestoreCategoryDataSource {
    suspend fun saveCategory(uid: String, category: Category)

    fun fetchAllCategory(uid: String): Flow<List<Category>>

    suspend fun deleteCategory(uid: String, category: Category)
}

class DefaultFirestoreCategoryDataSource(
    private val firestore: FirebaseFirestore
) : FirestoreCategoryDataSource {

    override suspend fun saveCategory(uid: String, category: Category) {
        firestore.categoriesRef(uid).document().set(category.toEntity())
    }

    @ExperimentalCoroutinesApi
    override fun fetchAllCategory(uid: String) = callbackFlow {
        firestore.categoriesRef(uid).addSnapshotListener { snapshot, exception ->
            exception?.message?.run { return@addSnapshotListener }

            val categoryEntityList = snapshot?.toObjects(CategoryEntity::class.java)
            categoryEntityList ?: return@addSnapshotListener

            val categoryList = categoryEntityList.mapNotNull { it.modelOrNull() }
            offer(categoryList)
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