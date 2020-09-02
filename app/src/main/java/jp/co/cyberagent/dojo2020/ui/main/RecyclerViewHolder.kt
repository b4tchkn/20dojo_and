package jp.co.cyberagent.dojo2020.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.R
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.FieldPosition

class RecyclerViewHolder(view:View):RecyclerView.ViewHolder(view) {
    interface ItemClickListner{
        fun onItemClick(view:View,position: Int)
    }
    val itemTextView:TextView = view.findViewById(R.id.itemImageView)
    val itemImageView: ImageView = view.findViewById(R.id.itemImageView)



}