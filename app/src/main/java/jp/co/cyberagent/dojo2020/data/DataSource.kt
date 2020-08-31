package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun insert(memo: Memo)

    suspend fun fetchAll(): Flow<List<Memo>>
}