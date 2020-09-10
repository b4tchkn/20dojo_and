package jp.co.cyberagent.dojo2020.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.co.cyberagent.dojo2020.R
import jp.co.cyberagent.dojo2020.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            homeToCreateButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_memoCreateFragment)
            }

            homeToProfileButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_profile_icon_id -> {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                true
            }
            R.id.home_create_icon_id -> {
                findNavController().navigate(R.id.action_homeFragment_to_memoCreateFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }


}