package jp.co.cyberagent.dojo2020.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.co.cyberagent.dojo2020.R

class HomeFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.activity_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editbutton = view.findViewById<Button>(R.id.edit_button)
        editbutton?.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_memoEditFragment,null)
        }
        val probutton = view.findViewById<Button>(R.id.profile_button)
        probutton?.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment,null)
        }
    }

}