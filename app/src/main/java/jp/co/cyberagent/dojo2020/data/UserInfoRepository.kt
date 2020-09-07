package jp.co.cyberagent.dojo2020.data

import com.google.firebase.auth.FirebaseUser
import jp.co.cyberagent.dojo2020.data.model.FirebaseUserInfo
import jp.co.cyberagent.dojo2020.data.remote.auth.FirebaseAuthentication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserInfoRepository {
    fun fetchUserInfo(): Flow<FirebaseUserInfo?>
}

class DefaultUserInfoRepository : UserInfoRepository {
    init {
        FirebaseAuthentication.addStateListener { firebaseAuth ->
            firebaseUser = firebaseAuth.currentUser
        }
    }

    private var firebaseUser: FirebaseUser? = null

    override fun fetchUserInfo(): Flow<FirebaseUserInfo?> = flow {
        if (firebaseUser == null || firebaseUser?.uid == null || firebaseUser?.email == null) {
            emit(null)
            return@flow
        }

        val user = firebaseUser ?: return@flow

        val email = user.email ?: return@flow
        val uid = user.uid

        emit(FirebaseUserInfo(uid, email))
    }

}