package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.remote.FireStoreDataSource
import kotlinx.coroutines.flow.Flow

class Repository(
    private val localDataSource: DataSource,
    private val remoteDataSource: FireStoreDataSource
) : DataSource {

    override suspend fun save(memo: Memo) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAll(): Flow<List<Memo>> {
        TODO("Not yet implemented")
    }
}