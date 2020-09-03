package jp.co.cyberagent.dojo2020.ui.home

import android.content.Context
import androidx.lifecycle.*
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.model.FirebaseUserInfo
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.data.remote.auth.FirebaseUserInfoDataSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {
    private val memoRepository = DI.injectRepository(context)
    private val firebaseUserInfoRepository: FirebaseUserInfoDataSource = TODO()

    val userInfoLiveData = liveData<FirebaseUserInfo> {
        emitSource(firebaseUserInfoRepository.fetchFirebaseUserInfo().asLiveData())
    }

    val memoListLiveData = liveData<List<Memo>> {
        userInfoLiveData.asFlow().collect { userInfo ->
            emitSource(memoRepository.fetchAll(userInfo.uid).asLiveData())
        }
    }

    fun saveMemo(memo: Memo) = viewModelScope.launch {
        userInfoLiveData.asFlow().collect { userInfo ->
            memoRepository.save(userInfo.uid, memo)
        }
    }
}