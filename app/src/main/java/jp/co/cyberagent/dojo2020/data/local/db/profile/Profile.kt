package jp.co.cyberagent.dojo2020.data.local.db.profile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profileDao: ProfileEntity)

    @Query(value = "SELECT * FROM profile")
    fun fetch(): Flow<ProfileEntity?>

    @Query("DELETE FROM profile")
    suspend fun delete()
}