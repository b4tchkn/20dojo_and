package jp.co.cyberagent.dojo2020.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import jp.co.cyberagent.dojo2020.R
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

                expandImageButton.setOnClickListener {
                    it.isSelected = !it.isSelected

                    text.contents.also { contents ->
                        contentsTextView.text =
                            if (it.isSelected) contents else contents.toOneLine()
                    }

                    expandImageButton.showImage(
                        binding,
                        if (it.isSelected) R.drawable.ic_expand_less else R.drawable.ic_expand_more
                    )
                }

                text.contents.also { contents ->
                    contentsTextView.text = contents.toOneLine().also {
                        expandImageButton.visibility =
                            visibleOrGone(it.takeLastWhile { ch -> ch == addedPostFix }.length >= 3)
                    }
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
            private const val addedPostFix = '.'
        }

        private fun ImageButton.showImage(viewBinding: ViewBinding, @DrawableRes drawableId: Int) {
            val drawable = binding.root.context.getDrawable(drawableId)

            Glide.with(viewBinding.root).load(drawable).into(this)
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