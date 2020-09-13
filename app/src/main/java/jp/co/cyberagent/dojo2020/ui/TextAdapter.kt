package jp.co.cyberagent.dojo2020.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2020.data.model.*
import jp.co.cyberagent.dojo2020.databinding.ItemMemoBinding
import java.util.Collections.emptyList

class TextAdapter(private val onItemClickListener: View.OnClickListener) :
    RecyclerView.Adapter<TextAdapter.RecyclerViewHolder>() {

    var textList: List<Text> = emptyList()
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

        fun setText(text: Text) {
            binding.apply {
                titleTextView.text = text.title
                categoryTextView.text = text.category

                text.contents.toOneLine().also { contents ->
                    contentsTextView.text = contents

                    expandImageView.visibility = visibleOrGone(
                        contents.takeLastWhile { it == '.' }.length >= 3
                    )

                }

            }

            when (text) {
                is Left -> setDraft(text.value)
                is Right -> setMemo(text.value)
            }
        }

        private fun setDraft(draft: Draft) {

        }

        private fun setMemo(memo: Memo) {

        }

        private fun visibleOrGone(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.GONE

        private fun String.toOneLine(): String =
            if (this.contains("\n")) takeWhile { it != '\n' } + stringTerminated else this

        companion object {
            private const val stringTerminated = "..."
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMemoBinding = ItemMemoBinding.inflate(inflater, parent, false)

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val text = textList[position]

        holder.setText(text)
        holder.setOnItemClickListener(onItemClickListener)
    }

    override fun getItemCount() = textList.size
}