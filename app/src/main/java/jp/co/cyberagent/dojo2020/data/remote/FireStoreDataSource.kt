package jp.co.cyberagent.dojo2020.data.remote

import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow

interface FireStoreDataSource {
    suspend fun save(uid: String, memo: Memo)

    suspend fun fetchAll(uid: String): Flow<List<Memo>>
}