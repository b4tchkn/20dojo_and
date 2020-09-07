package jp.co.cyberagent.dojo2020.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.R
import jp.co.cyberagent.dojo2020.databinding.RecyclerviewMemoItemBinding
import jp.co.cyberagent.dojo2020.models.Memo

class RecyclerMemoAdapter(initList: List<Memo> = emptyList()) : RecyclerView.Adapter<RecyclerMemoAdapter.RecyclerViewHolder>() {

    var memoList: List<Memo> = initList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecyclerviewMemoItemBinding = DataBindingUtil.inflate<RecyclerviewMemoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_memo_item,
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    class RecyclerViewHolder(val binding: RecyclerviewMemoItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val memo = memoList[position]
        holder.binding.viewModel = Memo(memo.id,memo.title,memo.hour,memo.minute,memo.description)
    }

    fun setMemo(memo: List<Memo>) {
        this.memoList = memo
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = memoList.size

}