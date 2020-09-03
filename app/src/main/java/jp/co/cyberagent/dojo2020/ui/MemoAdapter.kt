package jp.co.cyberagent.dojo2020.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.databinding.ItemMemoBinding

class MemoAdapter(initialList: List<String>) : RecyclerView.Adapter<MemoAdapter.RecyclerViewHolder>() {

    var memoList: List<String> = initialList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class RecyclerViewHolder(
        private val binding: ItemMemoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setMemo(memo: Memo) {
            binding.apply {
                itemTextView.text = memo.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMemoBinding = ItemMemoBinding.inflate(inflater)

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val memo = memoList[position]

        holder.setMemo(Memo(memo, "contents", 0.01))
    }

    override fun getItemCount() = memoList.size

}