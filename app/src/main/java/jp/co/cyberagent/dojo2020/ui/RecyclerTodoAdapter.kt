package jp.co.cyberagent.dojo2020.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.R
import jp.co.cyberagent.dojo2020.databinding.RecyclerviewTodoItemBinding
import jp.co.cyberagent.dojo2020.models.Todo

class RecyclerTodoAdapter(initList: List<Todo> = emptyList()) : RecyclerView.Adapter<RecyclerTodoAdapter.RecyclerViewHolder>() {

    var todoList: List<Todo> = initList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecyclerviewTodoItemBinding = DataBindingUtil.inflate<RecyclerviewTodoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_todo_item,
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    class RecyclerViewHolder(val binding: RecyclerviewTodoItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val todo = todoList[position]
        holder.binding.viewModel = Todo(todo.id,todo.title,todo.hour,todo.minute,todo.description)
    }

    fun setTodo(todo: List<Todo>) {
        this.todoList = todo
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = todoList.size

}