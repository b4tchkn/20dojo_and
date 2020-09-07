package jp.co.cyberagent.dojo2020

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import jp.co.cyberagent.dojo2020.models.Todo
import jp.co.cyberagent.dojo2020.ui.list.TodoListViewModel
import jp.co.cyberagent.dojo2020.ui.list.TodoListViewModelFactory

class InputMemoFragment: Fragment() {

    private val todoListViewModel by viewModels<TodoListViewModel> {
        TodoListViewModelFactory(
            this,
            Bundle(),
            this.requireContext()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_input_memo ,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val submitButton = view.findViewById<Button>(R.id.btInputMemoSubmit)
        submitButton.setOnClickListener {
            val title = view.findViewById<EditText>(R.id.etInputMemoTitle).text.toString()
            val hour = view.findViewById<EditText>(R.id.etInputMemoHour).text.toString()
            val minute = view.findViewById<EditText>(R.id.etInputMemoMinute).text.toString()
            val description = view.findViewById<EditText>(R.id.etDescription).text.toString()
//                if (title != null) {
////                    makeToast
//                } else {
//                    Log.i("test", "null")
//                }
            val todo = Todo(0, title, hour.toInt(), minute.toInt(), description)
            todoListViewModel.saveTodo(todo)
        }
    }
}