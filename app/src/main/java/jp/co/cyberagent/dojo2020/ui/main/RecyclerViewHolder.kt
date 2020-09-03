package jp.co.cyberagent.dojo2020.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.R

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val itemTextView: TextView = view.findViewById(R.id.item_text_view)
}