package jp.co.cyberagent.dojo2020.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.model.Memo
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {
    private val repository = DI.injectRepository(context)

    val liveData = liveData { emitSource(repository.fetchAll().asLiveData()) }

    fun saveMemo(memo: Memo) = viewModelScope.launch {
        repository.save(memo)
    }
}