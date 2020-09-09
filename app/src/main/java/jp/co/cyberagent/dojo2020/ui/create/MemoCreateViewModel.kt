package jp.co.cyberagent.dojo2020.ui.create

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.model.Draft
import kotlinx.coroutines.launch
import java.util.*

class MemoCreateViewModel(context: Context) : ViewModel() {
    private val draftRepository = DI.injectDefaultDraftRepository(context)

    val draftListLiveData = liveData<List<Draft>> {
        emitSource(draftRepository.fetchAllDraft().asLiveData())
    }

    fun addDraft(title: String, content: String, category: String) = viewModelScope.launch {
        val id = UUID.randomUUID().toString()
        val startTime = System.currentTimeMillis()
        val draft = Draft(id, title, content, startTime, category)

        draftRepository.saveDraft(draft)
    }

    fun addCategory(categoryName: String) = viewModelScope.launch {

    }
}