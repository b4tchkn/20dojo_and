package jp.co.cyberagent.dojo2020.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query( value = "SELECT * FROM todo_list")
    fun loadAllTodo(): LiveData<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todoEntity: TodoEntity)
}