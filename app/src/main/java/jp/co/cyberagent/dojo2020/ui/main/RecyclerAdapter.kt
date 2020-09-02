package jp.co.cyberagent.dojo2020.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.R

class RecyclerAdapter(
    private val myDataset: Recyclerviewmain,
    recyclerviewmain: Recyclerviewmain,
    a: MutableList<String>
):RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false) as TextView
        return RecyclerViewHolder(textView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemTextView.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size

}