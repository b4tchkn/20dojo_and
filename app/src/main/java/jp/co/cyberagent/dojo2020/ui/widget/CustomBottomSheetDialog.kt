package jp.co.cyberagent.dojo2020.ui.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jp.co.cyberagent.dojo2020.databinding.LayoutBottomSheetBinding

class CustomBottomSheetDialog : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "CustomBottomSheetDialog"
    }

    private lateinit var binding: LayoutBottomSheetBinding

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

}