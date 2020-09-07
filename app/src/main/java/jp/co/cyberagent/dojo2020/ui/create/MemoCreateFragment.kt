package jp.co.cyberagent.dojo2020.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.co.cyberagent.dojo2020.R
import jp.co.cyberagent.dojo2020.databinding.FragmentMemoCreateBinding

class MemoCreateFragment : Fragment() {
    private lateinit var binding: FragmentMemoCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoCreateBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            createToHomeButton.setOnClickListener {
                findNavController().navigate(R.id.action_createMemoFragment_to_homeFragment)
            }
        }

    }
}