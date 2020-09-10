package jp.co.cyberagent.dojo2020.ui.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jp.co.cyberagent.dojo2020.databinding.LayoutBottomSheetBinding
import jp.co.cyberagent.dojo2020.ui.create.MemoCreateViewModel
import jp.co.cyberagent.dojo2020.ui.create.MemoCreateViewModelFactory

class CustomBottomSheetDialog : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "CustomBottomSheetDialog"
    }

    private lateinit var binding: LayoutBottomSheetBinding
    private val memoCreateViewModel by activityViewModels<MemoCreateViewModel> {
        MemoCreateViewModelFactory(
            this,
            Bundle(),
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = LayoutBottomSheetBinding.inflate(inflater)

        binding.addCategoryEditText.requestFocus()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val onClick: (View) -> Unit = {
                val category = addCategoryEditText.text.toString()

                memoCreateViewModel.addCategory(category)
                dismiss()
            }

            addCategoryButton.setOnClickListener(onClick)
        }
    }
}