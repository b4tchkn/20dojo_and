package jp.co.cyberagent.dojo2020.data

import android.util.Log
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2020.models.Memo

interface Repository {
    suspend fun inputMemo(memo: Memo)
    fun loadAllMemo(): LiveData<List<Memo>>
}

class DefaultRepository(
    private val localDataSource: MemoDataSource
):Repository {
    override suspend fun inputMemo(memo: Memo) {
        Log.i("test: in Repository", memo.title)
        localDataSource.inputMemo(memo)
    }

    override fun loadAllMemo(): LiveData<List<Memo>> {
        val localMemoList = localDataSource.loadAllMemo()


        return localMemoList
    }
}