package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoDataSource {
    suspend fun saveMemo(memo: Memo)

    suspend fun fetchAllMemo(): Flow<List<Memo>>
}