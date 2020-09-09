package jp.co.cyberagent.dojo2020.ui.create.spinner

import android.view.View
import android.widget.AdapterView

class CustomOnItemSelectedListener(
    private val createCategory: () -> Unit
) : AdapterView.OnItemSelectedListener {

    private var isFirstTime = true

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (!isFirstTime && position == 0) createCategory()

        isFirstTime = false
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        isFirstTime = false
    }
}