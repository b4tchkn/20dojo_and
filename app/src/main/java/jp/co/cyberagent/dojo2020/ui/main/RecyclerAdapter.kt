package jp.co.cyberagent.dojo2020.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.R

class RecyclerAdapter(private val list: List<String>):RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return RecyclerViewHolder(View)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemTextView.text = list[position]
    }

    override fun getItemCount() = list.size

}