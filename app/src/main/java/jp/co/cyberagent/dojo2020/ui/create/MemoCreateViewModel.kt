package jp.co.cyberagent.dojo2020.ui.create

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.ext.accessWithUid
import jp.co.cyberagent.dojo2020.data.model.Category
import jp.co.cyberagent.dojo2020.data.model.Draft
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

class MemoCreateViewModel(context: Context) : ViewModel() {
    private val draftRepository = DI.injectDefaultDraftRepository(context)
    private val categoryRepository = DI.injectDefaultCategoryRepository(context)
    private val userInfoRepository = DI.injectDefaultUserInfoRepository()

    private val userInfoFlow = userInfoRepository.fetchUserInfo()

    @ExperimentalCoroutinesApi
    val categoryListLiveData = userInfoFlow.flatMapLatest { firebaseUserInfo ->
        val uid = firebaseUserInfo?.uid

        val categoryListFlow = categoryRepository.fetchAllCategory(uid)
        val categorySetFlow = categoryListFlow.map { it.toSet() }

        categorySetFlow
    }.asLiveData()

    fun addDraft(title: String, content: String, category: String) = viewModelScope.launch {
        val id = UUID.randomUUID().toString()
        val startTime = System.currentTimeMillis()
        val draft = Draft(id, title, content, startTime, Category(category))

        draftRepository.saveDraft(draft)
    }

    fun addCategory(categoryName: String) = viewModelScope.launch {

        userInfoFlow.accessWithUid { uid ->
            categoryRepository.saveCategory(
                uid,
                Category(categoryName)
            )
        }
    }
}