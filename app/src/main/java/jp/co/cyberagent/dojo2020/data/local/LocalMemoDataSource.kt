package jp.co.cyberagent.dojo2020.data.local

import jp.co.cyberagent.dojo2020.data.MemoDataSource
import jp.co.cyberagent.dojo2020.data.local.db.MemoDataBase
import jp.co.cyberagent.dojo2020.data.local.db.MemoEntity
import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalMemoDataSource(private val dataBase: MemoDataBase) : MemoDataSource {

    override suspend fun save(memo: Memo) {
        val forInsertMemo = MemoEntity.createForInsert(memo.title, memo.contents, memo.time)

        dataBase.memoDao().insert(forInsertMemo)
    }

    override suspend fun fetchAll(): Flow<List<Memo>> {
        return dataBase.memoDao().fetchAll().map { memoEntityList ->
            memoEntityList.map { Memo(it.title, it.contents, it.time) }
        }
    }
}