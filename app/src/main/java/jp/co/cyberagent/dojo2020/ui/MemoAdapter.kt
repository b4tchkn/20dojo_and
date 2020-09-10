package jp.co.cyberagent.dojo2020.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.databinding.ItemMemoBinding
import java.util.Collections.emptyList

class MemoAdapter(private val onItemClickListener: View.OnClickListener) :
    RecyclerView.Adapter<MemoAdapter.RecyclerViewHolder>() {

    var memoList: List<Memo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class RecyclerViewHolder(
        private val binding: ItemMemoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setOnItemClickListener(onItemClickListener: View.OnClickListener) {
            itemView.setOnClickListener(onItemClickListener)
        }

        fun setMemo(memo: Memo) {
            binding.apply {
                titleTextView.text = memo.title
                contentsTextView.text = memo.contents
                timeChronometer.text = memo.time.toString()
                categoryTextView.text = memo.category
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMemoBinding = ItemMemoBinding.inflate(inflater, parent, false)

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val memo = memoList[position]

        holder.setMemo(memo)
        holder.setOnItemClickListener(onItemClickListener)
    }

    override fun getItemCount() = memoList.size


}