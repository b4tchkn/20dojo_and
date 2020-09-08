package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun saveProfile(uid: String?, profile: Profile)

    suspend fun fetchProfile(uid: String?): Flow<Profile?>
}

class DefaultProfileRepository() {

}
