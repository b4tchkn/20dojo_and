package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoDataSource {
    suspend fun save(memo: Memo)

    suspend fun fetchAll(): Flow<List<Memo>>
}