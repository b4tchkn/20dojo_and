package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.remote.firestore.FireStoreDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

interface MemoRepository {
    suspend fun saveMemo(uid: String?, memo: Memo)

    suspend fun fetchAllMemo(uid: String?): Flow<List<Memo>>

    suspend fun deleteMemoById(uid: String?, id: String)
}

class DefaultMemoRepository(
    private val localMemoDataSource: MemoDataSource,
    private val remoteDataSource: FireStoreDataSource
) : MemoRepository {

    override suspend fun saveMemo(uid: String?, memo: Memo) {
        localMemoDataSource.saveMemo(memo)
        uid ?: return

        remoteDataSource.saveMemo(uid, memo)
    }

    @FlowPreview
    override suspend fun fetchAllMemo(uid: String?): Flow<List<Memo>> {
        val localMemoListFlow = localMemoDataSource.fetchAllMemo()

        val memoListFlow = localMemoListFlow.flatMapConcat { localMemoList ->
            if (uid != null && localMemoList.isEmpty()) {
                return@flatMapConcat remoteDataSource.fetchAllMemo(uid)
            }

            return@flatMapConcat localMemoDataSource.fetchAllMemo()
        }

        return memoListFlow
    }

    override suspend fun deleteMemoById(uid: String?, id: String) {
        localMemoDataSource.deleteMemoById(id)
        uid ?: return

        remoteDataSource.deleteMemoById(uid, id)
    }
}