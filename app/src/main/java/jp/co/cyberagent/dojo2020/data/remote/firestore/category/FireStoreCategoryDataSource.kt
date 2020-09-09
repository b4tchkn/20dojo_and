package jp.co.cyberagent.dojo2020.data.remote.firestore.category

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.cyberagent.dojo2020.data.remote.firestore.FireStoreConstants
import jp.co.cyberagent.dojo2020.data.remote.firestore.categoriesRef
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

interface FirestoreCategoryDataSource {
    suspend fun saveCategory(uid: String, category: String)

    suspend fun fetchAllCategory(uid: String): Flow<List<String>>

    suspend fun deleteCategory(uid: String, category: String)
}

class DefaultFirestoreCategoryDataSource(private val firestore: FirebaseFirestore) :
    FirestoreCategoryDataSource {

    override suspend fun saveCategory(uid: String, category: String) {
        val entity = CategoryEntity(category)

        firestore.categoriesRef(uid).document().set(entity)
    }

    override suspend fun fetchAllCategory(uid: String) = flow<List<String>> {
        try {
            val snapshot = firestore.categoriesRef(uid).get().await()

            val categoryEntityList = snapshot.toObjects(CategoryEntity::class.java)

            emit(categoryEntityList.mapNotNull { it.name })
        } catch (e: Exception) {
            //Todo(emit failed)
        }
    }

    override suspend fun deleteCategory(uid: String, category: String) {
        val snapshot = firestore.categoriesRef(uid)
            .whereEqualTo(FireStoreConstants.CATEGORY, category)
            .get()
            .await()

        snapshot.forEach {
            it.reference.delete()
        }
    }

}