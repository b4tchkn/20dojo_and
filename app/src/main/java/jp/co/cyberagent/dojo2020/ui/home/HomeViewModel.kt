package jp.co.cyberagent.dojo2020.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.data.DefaultUserInfoRepository
import jp.co.cyberagent.dojo2020.data.UserInfoRepository
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.test.FakeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {
    private val memoRepository = FakeRepository
    private val firebaseUserInfoRepository: UserInfoRepository = DefaultUserInfoRepository()

    private fun user() = firebaseUserInfoRepository.fetchUserInfo()

    val memoListLiveData = liveData<List<Memo>> {
        user().collect { userInfo ->
            emitSource(memoRepository.fetchAllMemo(userInfo?.uid).asLiveData())
        }
    }

    fun filter() = viewModelScope.launch {
        user().collect { userInfo ->
            memoRepository.fetchAllMemo(userInfo?.uid).collect { memoList ->
                memoList.filter { it.category == "kotlin" }
            }
        }
    }

    fun saveMemo(memo: Memo) = viewModelScope.launch {
        user().collect { userInfo ->
            memoRepository.saveMemo(userInfo?.uid, memo)
            Log.d(TAG, "uid is ${userInfo?.uid}")
        }
    }
}