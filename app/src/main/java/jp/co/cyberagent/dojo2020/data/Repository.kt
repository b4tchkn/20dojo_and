package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.remote.FireStoreDataSource
import kotlinx.coroutines.flow.Flow

class Repository(
    private val localDataSource: DataSource,
    private val remoteDataSource: FireStoreDataSource
) : DataSource {

    suspend fun save(memo: Memo) {
        localDataSource.save(memo)
    }

    suspend fun fetchAll(): Flow<List<Memo>> {
        return localDataSource.fetchAll()
    }
}