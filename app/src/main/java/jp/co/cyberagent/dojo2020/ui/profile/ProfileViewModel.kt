package jp.co.cyberagent.dojo2020.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.DefaultUserInfoRepository
import jp.co.cyberagent.dojo2020.data.ProfileRepository
import jp.co.cyberagent.dojo2020.data.UserInfoRepository
import jp.co.cyberagent.dojo2020.data.model.Profile
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {

    private val firebaseUserInfoRepository: UserInfoRepository = DefaultUserInfoRepository()
    private val firebaseProfileRepository: ProfileRepository = DI.injectTestProfileRepository()
    private val memoRepository = DI.injectDefaultMemoRepository(context)

    private fun user() = firebaseUserInfoRepository.fetchUserInfo()

    private val mutableLiveData: MutableLiveData<Profile> = MutableLiveData()
    val liveData: LiveData<Profile>
        get() = mutableLiveData

    fun fetchUserData() = viewModelScope.launch {
        user().collect { userInfo ->
            firebaseProfileRepository.fetchProfile(userInfo?.uid).collect {
                mutableLiveData.value = it
            }
        }
    }

    fun fetchStudyTime() = viewModelScope.launch {
        user().collect { userInfo ->
            memoRepository.fetchAllMemo(userInfo?.uid).collect { memoList ->
                //val noneList = memoList.filter { it.category == "none"}
                //Log.d(TAG, noneList.first().toString())
            }
        }
    }
}