package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow

class Repository(localDataSource: DataSource, remoteDataSource: DataSource) : DataSource {
    override suspend fun insert(memo: Memo) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAll(): Flow<List<Memo>> {
        TODO("Not yet implemented")
    }
}