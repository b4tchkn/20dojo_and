package jp.co.cyberagent.dojo2020.data.remote.firestore.memo

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.remote.firestore.memosRef
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

interface FireStoreMemoDataSource {
    suspend fun saveMemo(uid: String, memo: Memo)

    suspend fun fetchAllMemo(uid: String): Flow<List<Memo>>

    suspend fun fetchMemoById(uid: String, id: String): Flow<Memo?>

    suspend fun deleteMemoById(uid: String, id: String)
}

class DefaultFireStoreMemoDataSource(private val firestore: FirebaseFirestore) :
    FireStoreMemoDataSource {

    override suspend fun saveMemo(uid: String, memo: Memo) {
        val entity = memo.toEntityForRemote()
        val id = entity.id ?: return

        firestore.memosRef(uid).document(id).set(entity)
    }

    override suspend fun fetchAllMemo(uid: String) = flow<List<Memo>> {
        try {
            val snapshot = firestore.memosRef(uid).get().await()
            val memoEntityList = snapshot.toObjects(MemoEntity::class.java)
            val nullableMemoList =
                memoEntityList.map { entity -> entity.modelOrNull() } // if use state, throw exception

            val notNullMemoList = nullableMemoList.map { it ?: return@flow }

            emit(notNullMemoList)
        } catch (e: Exception) {
            //Todo(emit failed)
        }
    }

    override suspend fun fetchMemoById(uid: String, id: String) = flow<Memo?> {
        try {
            val snapshot = firestore.memosRef(uid).document(id).get().await()
            val memoEntity = snapshot.toObject(MemoEntity::class.java)
            val memo = memoEntity?.modelOrNull()

            emit(memo)
        } catch (e: Exception) {
            //Todo(emit failed)
        }
    }

    override suspend fun deleteMemoById(uid: String, id: String) {
        firestore.memosRef(uid).document(id).delete().await()
    }

    private fun Memo.toEntityForRemote(): MemoEntity {
        return MemoEntity(id, title, contents, time)
    }

    private fun MemoEntity.modelOrNull(): Memo? {
        val containsNull = listOf(id, title, contents, time, category).contains(null)
        if (containsNull) {
            return null
        }

        return Memo(id!!, title!!, contents!!, time!!, category!!)
    }

}