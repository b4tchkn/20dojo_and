package jp.co.cyberagent.dojo2020.data

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2020.models.Todo

interface DataSource {
    fun loadAllTodo(): LiveData<List<Todo>>

    fun inputMemo(todo: Todo)
}