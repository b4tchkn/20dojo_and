package jp.co.cyberagent.dojo2020.ui.test

import android.util.Log
import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.flow

object FakeRepository : jp.co.cyberagent.dojo2020.data.DataSource {
    override suspend fun insert(memo: Memo) {
        Log.d(Constants.REPOSITORY, "insert: $memo")
    }

    override suspend fun fetchAll() = flow {
        val dataList = MemoData.list

        dataList.forEach { Log.d(Constants.REPOSITORY, "fetchAll: $it") }
        emit(dataList)
    }
}