package jp.co.cyberagent.dojo2020.ui.list

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.Repository
import jp.co.cyberagent.dojo2020.models.Memo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoListViewModel(context: Context): ViewModel() {
    private val memoRepository: Repository
    val memoMutableList : LiveData<List<Memo>>

    init {
        memoRepository = DI.injectRepository(context)
        memoMutableList = memoRepository.loadAllMemo()
    }

    fun saveMemo(memo: Memo) = viewModelScope.launch(Dispatchers.IO) {
        memoRepository.inputMemo(memo)
        Log.i("test: in ViewModel ", memo.title )
    }

    fun loadAllMemo() {
        viewModelScope.launch {
            val memoData = memoRepository.loadAllMemo()
        }
    }
}