package jp.co.cyberagent.dojo2020.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.DefaultUserInfoRepository
import jp.co.cyberagent.dojo2020.data.UserInfoRepository
import jp.co.cyberagent.dojo2020.data.ext.accessWithUid
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.model.toText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {
    private val draftRepository = DI.injectDefaultDraftRepository(context)
    private val memoRepository = DI.injectDefaultMemoRepository(context)
    private val firebaseUserInfoRepository: UserInfoRepository = DefaultUserInfoRepository()

    private val userFlow = firebaseUserInfoRepository.fetchUserInfo()

    val textListLiveData = liveData {
        userFlow.accessWithUid { uid ->
            emitSource(draftRepository.fetchAllDraft()
                .combine(memoRepository.fetchAllMemo(uid)) { draftList, memoList ->
                    val rightList = memoList.map { it.toText() }
                    val leftList = draftList.map { it.toText() }

                    leftList + rightList
                }
                .asLiveData()
            )
        }
    }

    fun filter() = viewModelScope.launch {
        userFlow.collect { userInfo ->
            memoRepository.fetchAllMemo(userInfo?.uid).collect { memoList ->
                memoList.filter { it.category == "kotlin" }
            }
        }
    }

    fun saveMemo(memo: Memo) = viewModelScope.launch {
        userFlow.collect { userInfo ->
            memoRepository.saveMemo(userInfo?.uid, memo)
            Log.d(TAG, "uid is ${userInfo?.uid}")
        }
    }
}