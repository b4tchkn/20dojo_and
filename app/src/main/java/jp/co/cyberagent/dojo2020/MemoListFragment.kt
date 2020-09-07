package jp.co.cyberagent.dojo2020

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.cyberagent.dojo2020.databinding.MemoListTabBinding
import jp.co.cyberagent.dojo2020.ui.RecyclerTodoAdapter
import jp.co.cyberagent.dojo2020.ui.list.TodoListViewModel
import jp.co.cyberagent.dojo2020.ui.list.TodoListViewModelFactory
import kotlinx.android.synthetic.main.memo_list_tab.view.*

class MemoListFragment: Fragment(){
    private lateinit var binding: MemoListTabBinding
    private val todoListViewModel by viewModels<TodoListViewModel> {
        TodoListViewModelFactory(
            this,
            Bundle(),
            this.requireContext()
        )
    }

    lateinit var adapter: RecyclerTodoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = MemoListTabBinding.inflate(inflater,container,false)
        binding = MemoListTabBinding.inflate(layoutInflater)

        binding.viewModel = todoListViewModel
        val layout = LinearLayoutManager(context)
        binding.todoRecyclerView.layoutManager = layout
        binding.todoRecyclerView.adapter = RecyclerTodoAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoListViewModel.todoMutableList.observe(viewLifecycleOwner, Observer {
            val adapter = binding.todoRecyclerView.adapter as RecyclerTodoAdapter?
            adapter?.setTodo(it)
        })
        this.binding = binding

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_navi_memo_list_to_input_memo_data)
        }
    }
}