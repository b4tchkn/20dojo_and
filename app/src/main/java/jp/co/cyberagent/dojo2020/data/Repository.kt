package jp.co.cyberagent.dojo2020.data

import android.util.Log
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2020.models.Todo

interface Repository {
    suspend fun inputTodo(todo: Todo)
    fun loadAllTodo(): LiveData<List<Todo>>
}

class DefaultRepository(
    private val localDataSorce: DataSource
):Repository {
    override suspend fun inputTodo(todo: Todo) {
        Log.i("test: in Repository", todo.title)
        localDataSorce.inputMemo(todo)
    }

    override fun loadAllTodo(): LiveData<List<Todo>> {
        val localTodoList = localDataSorce.loadAllTodo()


        return localTodoList
    }
}