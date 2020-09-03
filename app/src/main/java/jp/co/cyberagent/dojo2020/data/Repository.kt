package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.remote.FireStoreDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

interface Repository {
    suspend fun save(uid: String?, memo: Memo)

    suspend fun fetchAll(uid: String?): Flow<List<Memo>>
}

class DefaultRepository(
    private val localDataSource: DataSource,
    private val remoteDataSource: FireStoreDataSource
) : Repository {

    override suspend fun save(uid: String?, memo: Memo) {
        localDataSource.save(memo)
        val userId = uid ?: return

        remoteDataSource.save(userId, memo)
    }

    @FlowPreview
    override suspend fun fetchAll(uid: String?): Flow<List<Memo>> {
        val localMemoListFlow = localDataSource.fetchAll()

        val memoListFlow = localMemoListFlow.flatMapConcat { localMemoList ->
            if (uid != null && localMemoList.isEmpty()) {
                return@flatMapConcat remoteDataSource.fetchAll(uid)
            }

            return@flatMapConcat localDataSource.fetchAll()
        }

        return memoListFlow
    }

}