package jp.co.cyberagent.dojo2020.ui.profile

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.model.Profile
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {

    private val firebaseUserInfoRepository = DI.injectDefaultUserInfoRepository()
    private val firebaseProfileRepository = DI.injectDefaultProfileRepository(context)
    private val memoRepository = DI.injectDefaultMemoRepository(context)

    private val userFlow = firebaseUserInfoRepository.fetchUserInfo()
    val firebaseUserInfo = userFlow.asLiveData()

    private val profileMutableLiveData: MutableLiveData<Profile> = MutableLiveData()
    val profileLiveData: LiveData<Profile> = profileMutableLiveData

    fun fetchUserData() = viewModelScope.launch {
        userFlow.collect { userInfo ->
            firebaseProfileRepository.fetchProfile(userInfo?.uid).collect {
                profileMutableLiveData.value = it
            }
        }
    }

    private val studyTimeMutableLiveData: MutableLiveData<Long> = MutableLiveData()
    val studyTimeLiveData: LiveData<Long> = studyTimeMutableLiveData

    fun calculateStudyTime() = viewModelScope.launch {
        userFlow.collect { userInfo ->
            memoRepository.fetchAllMemo(userInfo?.uid).collect { memoList ->
                val totalTime = memoList.fold(0L) { result: Long, memo: Memo ->

                    result + memo.time
                }

                Log.d(TAG, totalTime.toString())
            }
        }
    }
}