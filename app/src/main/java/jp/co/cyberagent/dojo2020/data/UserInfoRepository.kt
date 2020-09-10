package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.FirebaseUserInfo
import jp.co.cyberagent.dojo2020.data.remote.auth.FirebaseAuthentication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserInfoRepository {
    fun fetchUserInfo(): Flow<FirebaseUserInfo?>
}

class DefaultUserInfoRepository : UserInfoRepository {

    @ExperimentalCoroutinesApi
    override fun fetchUserInfo(): Flow<FirebaseUserInfo?> {
        return FirebaseAuthentication.currentUser.map { firebaseUser ->
            val user = firebaseUser ?: return@map null

            val email = user.email ?: return@map null
            val uid = user.uid

            FirebaseUserInfo(email, uid)
        }
    }
}