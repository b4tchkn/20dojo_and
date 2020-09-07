package jp.co.cyberagent.dojo2020.test

import android.util.Log
import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.flow

object FakeRepository : jp.co.cyberagent.dojo2020.data.MemoDataSource {
    override suspend fun saveMemo(memo: Memo) {
        Log.d(Constants.REPOSITORY, "insert: $memo")
    }

    override suspend fun fetchAllMemo() = flow {
        val dataList = MemoData.list

        dataList.forEach { Log.d(Constants.REPOSITORY, "fetchAll: $it") }
        emit(dataList)
    }
}