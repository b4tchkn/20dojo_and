package jp.co.cyberagent.dojo2020.data.local.db.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryEntity: CategoryEntity)

    @Query(value = "SELECT * FROM categories")
    fun fetchAll(): Flow<List<CategoryEntity>>

    @Query("DELETE FROM categories WHERE name = :name")
    suspend fun delete(name: String)
}