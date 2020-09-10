package jp.co.cyberagent.dojo2020.ui.create.spinner

import android.content.Context
import android.widget.ArrayAdapter
import jp.co.cyberagent.dojo2020.R

object SpinnerAdapter {
    private var INSTANCE: ArrayAdapter<String>? = null

    fun defaultItemList(context: Context): MutableList<String> {
        return context.resources.getStringArray(R.array.spinner_item).toMutableList()
    }

    fun getInstance(context: Context): ArrayAdapter<String> {
        if (INSTANCE != null) return INSTANCE!!
        val stringList = defaultItemList(context)

        INSTANCE = ArrayAdapter(context, android.R.layout.simple_spinner_item, stringList)

        return INSTANCE!!
    }
}