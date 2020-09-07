package jp.co.cyberagent.dojo2020.data.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import jp.co.cyberagent.dojo2020.data.DataSource
import jp.co.cyberagent.dojo2020.data.local.db.AppDatabase
import jp.co.cyberagent.dojo2020.data.local.db.TodoEntity
import jp.co.cyberagent.dojo2020.models.Todo


class LocalDataSource (private val database: AppDatabase):
    DataSource {
    override fun loadAllTodo(): LiveData<List<Todo>> {
        Log.i("test:", "in LocalDatasource loadAllTodo()" )
        return database.todoDao().loadAllTodo().map {
            todo ->
                todo.map { Todo(it.id, it.title, it.hour, it.minute, it.description)}
        }
    }

    override fun inputMemo (todo: Todo) {
        val forInsertTodo = TodoEntity.createForInsert(todo.title, todo.hour, todo.minute, todo.description)
        Log.i("test:LocalDatasource", todo.title )

        database.todoDao().insert(forInsertTodo)
    }
}