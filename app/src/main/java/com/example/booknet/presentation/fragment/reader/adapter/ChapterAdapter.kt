package com.example.booknet.presentation.fragment.reader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booknet.databinding.ItemChapterBinding
import com.example.booknet.domain.model.BookChapters
import com.example.booknet.util.logD
import com.example.booknet.util.setOnSingleClickListener
import kotlin.properties.Delegates

class ChapterAdapter(private val listener: (BookChapters) -> Unit) :
    RecyclerView.Adapter<ChapterAdapter.ChapterVH>() {

    private var items = listOf<BookChapters>()

    private var selectedPosition by Delegates.observable(-1) { _, oldPos, newPos ->
        notifyItemChanged(oldPos)
        notifyItemChanged(newPos)
    }

    fun setSelected(selected: Int) {
        selectedPosition = selected
    }

    fun updateData(data: List<BookChapters>?) {
        this.items = data ?: emptyList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterAdapter.ChapterVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChapterBinding.inflate(inflater, parent, false)
        return ChapterVH(binding)
    }

    override fun onBindViewHolder(holder: ChapterVH, position: Int) {
        holder.bind(items[position])
    }

    inner class ChapterVH(private val binding: ItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookChapters) {

            val text = item.text
            logD("text length = ${text?.length}")
            val text6000 = text?.substring(0, 6000)
            logD("text6000 length = ${text6000?.length}")
            binding.tvChapter.text = text6000

            itemView.setOnSingleClickListener {
                if (selectedPosition != adapterPosition) {
                    selectedPosition = adapterPosition
                    listener.invoke(item)
                }
            }
        }
    }
}