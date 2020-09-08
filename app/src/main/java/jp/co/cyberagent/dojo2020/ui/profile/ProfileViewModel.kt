package jp.co.cyberagent.dojo2020.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.DefaultUserInfoRepository
import jp.co.cyberagent.dojo2020.data.UserInfoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {
    private val userInfoRepository = DefaultUserInfoRepository()
    private val firebaseUserInfoRepository: UserInfoRepository =
        DI.injectDefaultUserInfoRepository()

    private fun user() = firebaseUserInfoRepository.fetchUserInfo()

    private val mutableLiveData: MutableLiveData<Profile> = MutableLiveData()
    val liveData: LiveData<Profile>
        get() = mutableLiveData

    fun fetchUserData() = viewModelScope.launch {
        userInfoRepository.fetchUserInfo().collect {
            val userData = Profile("abcde", "12345")
            mutableLiveData.value = userData
        }
    }
}