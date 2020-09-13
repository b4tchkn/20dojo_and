package jp.co.cyberagent.dojo2020.data.remote.firestore.profile

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.cyberagent.dojo2020.data.model.Profile
import jp.co.cyberagent.dojo2020.data.remote.firestore.profileRef
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

interface FireStoreProfileDataSource {
    suspend fun saveProfile(uid: String, profile: Profile)

    fun fetchProfile(uid: String): Flow<Profile?>
}

class DefaultFireStoreProfileDataSource(private val firestore: FirebaseFirestore) :
    FireStoreProfileDataSource {

    override suspend fun saveProfile(uid: String, profile: Profile) {
        firestore.profileRef(uid).set(profile)
    }

    override fun fetchProfile(uid: String) = flow {
        val result = firestore.profileRef(uid).get().await()

        val profileEntity = result.toObject(ProfileEntity::class.java) ?: return@flow

        emit(profileEntity.toModel())
    }

}