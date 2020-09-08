package jp.co.cyberagent.dojo2020.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.ProfileRepository
import jp.co.cyberagent.dojo2020.data.model.Profile
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {

    private val firebaseUserInfoRepository: ProfileRepository =
        DI.injectTestProfileRepository()

    private val mutableLiveData: MutableLiveData<Profile> = MutableLiveData()
    val liveData: LiveData<Profile>
        get() = mutableLiveData

    fun fetchUserData() = viewModelScope.launch {
        firebaseUserInfoRepository.fetchProfile(null).collect {
            mutableLiveData.value = it
        }
    }
}