package jp.co.cyberagent.dojo2020.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemoDao {
    @Query( value = "SELECT * FROM memo_list")
    fun loadAllMemo(): LiveData<List<MemoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memoEntity: MemoEntity)
}