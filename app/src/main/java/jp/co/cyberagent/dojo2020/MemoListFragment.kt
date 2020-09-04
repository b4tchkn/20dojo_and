package jp.co.cyberagent.dojo2020

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.memo_list_tab.view.*

class MemoListFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.memo_list_tab,container,false)

        view.btToInputMemo.setOnClickListener {
            findNavController().navigate(R.id.action_navi_memo_list_to_input_memo_data)
        }
        return view
    }
}