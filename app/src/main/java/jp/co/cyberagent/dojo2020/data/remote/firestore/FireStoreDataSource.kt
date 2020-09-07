package jp.co.cyberagent.dojo2020.data.remote.firestore

import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow

interface FireStoreDataSource {
    suspend fun saveMemo(uid: String, memo: Memo)

    suspend fun fetchAllMemo(uid: String): Flow<List<Memo>>
}