package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.remote.FireStoreDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

interface MemoRepository {
    suspend fun save(uid: String?, memo: Memo)

    suspend fun fetchAll(uid: String?): Flow<List<Memo>>
}

class DefaultMemoRepository(
    private val localMemoDataSource: MemoDataSource,
    private val remoteDataSource: FireStoreDataSource
) : MemoRepository {

    override suspend fun save(uid: String?, memo: Memo) {
        localMemoDataSource.save(memo)
        val userId = uid ?: return

        remoteDataSource.save(userId, memo)
    }

    @FlowPreview
    override suspend fun fetchAll(uid: String?): Flow<List<Memo>> {
        val localMemoListFlow = localMemoDataSource.fetchAll()

        val memoListFlow = localMemoListFlow.flatMapConcat { localMemoList ->
            if (uid != null && localMemoList.isEmpty()) {
                return@flatMapConcat remoteDataSource.fetchAll(uid)
            }

            return@flatMapConcat localMemoDataSource.fetchAll()
        }

        return memoListFlow
    }

}