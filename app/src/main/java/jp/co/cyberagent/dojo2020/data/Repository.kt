package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow

class Repository(
    private val localDataSource: DataSource,
    private val remoteDataSource: DataSource
) : DataSource {

    override suspend fun save(memo: Memo) {
        localDataSource.save(memo)
    }

    override suspend fun fetchAll(): Flow<List<Memo>> {
        return localDataSource.fetchAll()
    }
}