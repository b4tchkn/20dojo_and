package jp.co.cyberagent.dojo2020.data.local.db.draft

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(draftEntity: DraftEntity)

    @Query(value = "SELECT * FROM drafts")
    fun fetchAll(): Flow<List<DraftEntity>>

    @Query(value = "SELECT * FROM drafts WHERE id = :id")
    fun fetch(id: String): Flow<DraftEntity?>

    @Query("DELETE FROM drafts WHERE id = :id")
    suspend fun deleteById(id: String)
}