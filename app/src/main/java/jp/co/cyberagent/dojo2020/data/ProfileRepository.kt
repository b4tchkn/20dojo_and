package jp.co.cyberagent.dojo2020.data

import jp.co.cyberagent.dojo2020.data.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun saveProfile(profile: Profile)

    suspend fun fetchProfile(): Flow<Profile?>
}
