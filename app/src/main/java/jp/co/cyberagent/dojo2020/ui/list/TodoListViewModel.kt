package jp.co.cyberagent.dojo2020.ui.list

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import jp.co.cyberagent.dojo2020.DI
import jp.co.cyberagent.dojo2020.data.Repository
import jp.co.cyberagent.dojo2020.models.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(context: Context): ViewModel() {
    private val todoRepository: Repository

    fun saveTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.inputTodo(todo)
        Log.i("test: in ViewModel ", todo.title )
    }


    val todoMutableList : LiveData<List<Todo>>

    init {
        todoRepository = DI.injectRepository(context)
        todoMutableList = todoRepository.loadAllTodo()
    }

    fun loadAllTodo() {
        viewModelScope.launch {
            val todoData = todoRepository.loadAllTodo()
        }
    }

    init {
        loadAllTodo()
    }
}