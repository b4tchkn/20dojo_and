package jp.co.cyberagent.dojo2020.ui.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
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

    override fun onResume() {
        super.onResume()

        showKeyboard()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinner_item,
                android.R.layout.simple_spinner_item
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter

            addButton.setOnClickListener {
                findNavController().navigate(R.id.action_createMemoFragment_to_homeFragment)
            }
        }

    }

    private fun showKeyboard() {
        val activity = requireActivity()
        val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        manager.showSoftInput(binding.titleTextEdit, InputMethodManager.SHOW_IMPLICIT)
    }
}