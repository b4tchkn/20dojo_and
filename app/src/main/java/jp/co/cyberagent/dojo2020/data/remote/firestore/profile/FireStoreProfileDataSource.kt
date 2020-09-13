package jp.co.cyberagent.dojo2020.data.remote.firestore.profile

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.cyberagent.dojo2020.data.model.Profile
import jp.co.cyberagent.dojo2020.data.remote.firestore.profileRef
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface FireStoreProfileDataSource {
    suspend fun saveProfile(uid: String, profile: Profile)

    fun fetchProfile(uid: String): Flow<Profile?>
}

class DefaultFireStoreProfileDataSource(
    private val firestore: FirebaseFirestore
) : FireStoreProfileDataSource {

    override suspend fun saveProfile(uid: String, profile: Profile) {
        firestore.profileRef(uid).set(profile)
    }

    @ExperimentalCoroutinesApi
    override fun fetchProfile(uid: String) = callbackFlow {
        firestore.profileRef(uid).addSnapshotListener { snapshot, exception ->
            exception?.message?.run { return@addSnapshotListener }

            val profileEntity = snapshot?.toObject(ProfileEntity::class.java)
            profileEntity ?: return@addSnapshotListener

            offer(profileEntity.toModel())
        }
    }

}