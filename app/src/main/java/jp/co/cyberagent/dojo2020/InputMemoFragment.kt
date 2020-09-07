package jp.co.cyberagent.dojo2020

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import jp.co.cyberagent.dojo2020.models.Memo
import jp.co.cyberagent.dojo2020.ui.list.MemoListViewModel
import jp.co.cyberagent.dojo2020.ui.list.MemoListViewModelFactory

class InputMemoFragment: Fragment() {

    private val memoListViewModel by viewModels<MemoListViewModel> {
        MemoListViewModelFactory(
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
            val memo = Memo(0, title, hour.toInt(), minute.toInt(), description)
            memoListViewModel.saveMemo(memo)
        }
    }
}