package jp.co.cyberagent.dojo2020.data.local

import jp.co.cyberagent.dojo2020.data.local.db.ApplicationDataBase
import jp.co.cyberagent.dojo2020.data.local.db.memo.MemoEntity
import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface MemoDataSource {
    suspend fun saveMemo(memo: Memo)

    suspend fun fetchAllMemo(): Flow<List<Memo>>

    suspend fun fetchMemoById(id: String): Flow<Memo?>

    suspend fun deleteMemoById(id: String)
}

class DefaultMemoDataSource(private val dataBase: ApplicationDataBase) : MemoDataSource {

    override suspend fun saveMemo(memo: Memo) {
        dataBase.memoDao().insert(memo.toEntityForLocal())
    }

    override suspend fun fetchAllMemo(): Flow<List<Memo>> {
        return dataBase.memoDao().fetchAll().map { memoEntityList ->
            memoEntityList.map { it.toModel() }
        }
    }

    override suspend fun fetchMemoById(id: String): Flow<Memo?> {
        return dataBase.memoDao().fetch(id).map { it?.toModel() }
    }

    override suspend fun deleteMemoById(id: String) {
        dataBase.memoDao().deleteById(id)
    }

    private fun MemoEntity.toModel(): Memo {
        return Memo(id, title, contents, time, category)
    }

    private fun Memo.toEntityForLocal(): MemoEntity {
        return MemoEntity(id, title, contents, time, category)
    }
}