package jp.co.cyberagent.dojo2020.data.local

import jp.co.cyberagent.dojo2020.data.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    suspend fun saveProfile(profile: Profile)

    suspend fun fetchProfile(): Flow<Profile?>
}