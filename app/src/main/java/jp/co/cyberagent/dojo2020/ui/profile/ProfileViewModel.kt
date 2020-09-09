package jp.co.cyberagent.dojo2020.ui.profile

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.ProfileRepository
import jp.co.cyberagent.dojo2020.data.UserInfoRepository
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.model.Profile
import jp.co.cyberagent.dojo2020.test.FakeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {

    private val firebaseUserInfoRepository: UserInfoRepository =
        DI.injectDefaultUserInfoRepository()
    private val firebaseProfileRepository: ProfileRepository = DI.injectTestProfileRepository()
    private val memoRepository = FakeRepository

    private val userFlow = firebaseUserInfoRepository.fetchUserInfo()

    private val profileMutableLiveData: MutableLiveData<Profile> = MutableLiveData()
    val profileLiveData: LiveData<Profile>
        get() = profileMutableLiveData

    fun fetchUserData() = viewModelScope.launch {
        userFlow.collect { userInfo ->
            firebaseProfileRepository.fetchProfile(userInfo?.uid).collect {
                profileMutableLiveData.value = it
            }
        }
    }

    private val studyTimeMutableLiveData: MutableLiveData<Long> = MutableLiveData()
    val studyTimeLiveData: LiveData<Long>
        get() = studyTimeMutableLiveData

    fun culculateStudyTime() = viewModelScope.launch {
        userFlow.collect { userInfo ->
            memoRepository.fetchAllMemo(userInfo?.uid).collect { memoList ->
                val totalTime = memoList.fold(0L) { result: Long, memo: Memo ->

                    result + memo.time
                }
                //val noneList = memoList.filter { it.category == "none"}
                Log.d(TAG, totalTime.toString())
            }
        }
    }
}