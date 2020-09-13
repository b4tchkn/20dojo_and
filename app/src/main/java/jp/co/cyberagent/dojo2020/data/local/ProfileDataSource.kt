package jp.co.cyberagent.dojo2020.data.local

import jp.co.cyberagent.dojo2020.data.local.db.ApplicationDataBase
import jp.co.cyberagent.dojo2020.data.local.db.profile.ProfileEntity
import jp.co.cyberagent.dojo2020.data.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ProfileDataSource {
    suspend fun saveProfile(profile: Profile)

    fun fetchProfile(): Flow<Profile?>
}

class DefaultProfileDataSource(private val database: ApplicationDataBase) : ProfileDataSource {
    override suspend fun saveProfile(profile: Profile) {
        database.profileDao().insert(profile.toEntity())
    }

    override fun fetchProfile(): Flow<Profile?> {
        return database.profileDao().fetch().map { it?.toModel() }
    }

    private fun Profile.toEntity(): ProfileEntity {
        return ProfileEntity.createForInsert(name, iconUrl, accountList)
    }

}