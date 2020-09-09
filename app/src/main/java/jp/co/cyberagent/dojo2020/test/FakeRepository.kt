package jp.co.cyberagent.dojo2020.test

import android.util.Log
import jp.co.cyberagent.dojo2020.data.MemoRepository
import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.flow.flow

object FakeRepository : MemoRepository {
    override suspend fun saveMemo(uid: String?, memo: Memo) {
        Log.d(Constants.REPOSITORY, "insert: $memo")
    }

    override suspend fun fetchAllMemo(uid: String?) = flow {
        val dataList = MemoData.list

        dataList.forEach { Log.d(Constants.REPOSITORY, "fetchAll: $it") }
        emit(dataList)
    }

    override suspend fun fetchMemoById(uid: String?, id: String) = flow {
        emit(MemoData.list.firstOrNull { it.id == id })
    }

    override suspend fun fetchFilteredMemoByCategory(uid: String?, category: String) = flow {
        emit(MemoData.list.filter { it.category == category })
    }

    override suspend fun deleteMemoById(uid: String?, id: String) {
        MemoData.list.removeIf { it.id == id }
    }
}