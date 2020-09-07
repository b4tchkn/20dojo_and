package jp.co.cyberagent.dojo2020.data.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import jp.co.cyberagent.dojo2020.data.MemoDataSource
import jp.co.cyberagent.dojo2020.data.local.db.AppDatabase
import jp.co.cyberagent.dojo2020.data.local.db.MemoEntity
import jp.co.cyberagent.dojo2020.models.Memo


class LocalDataSource (private val database: AppDatabase):
    MemoDataSource {
    override fun loadAllMemo(): LiveData<List<Memo>> {
        Log.i("test:", "in LocalDatasource loadAllTodo()" )
        return database.memoDao().loadAllMemo().map {
            memo ->
                memo.map { Memo(it.id, it.title, it.hour, it.minute, it.description)}
        }
    }

    override fun inputMemo (memo: Memo) {
        val forInsertMemo = MemoEntity.createForInsert(memo.title, memo.hour, memo.minute, memo.description)
        Log.i("test:LocalDatasource", memo.title )

        database.memoDao().insert(forInsertMemo)
    }
}