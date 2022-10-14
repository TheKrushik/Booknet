package com.example.booknet.presentation.fragment.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.booknet.databinding.ItemBookBinding
import com.example.booknet.domain.model.Book

class BookAdapter(private val listener: (Book) -> Unit) :
    RecyclerView.Adapter<BookVH>() {

    var items: List<Book> = listOf()

    fun updateData(data: List<Book>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =
                items[oldPos] == data[newPos]

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean =
                items[oldPos].hashCode() == data[newPos].hashCode()

            override fun getOldListSize(): Int = items.size
            override fun getNewListSize(): Int = data.size
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookBinding.inflate(inflater, parent, false)
        return BookVH(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BookVH, position: Int) =
        holder.bind(items[position], listener)
}

class BookVH(private val binding: ItemBookBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Book, listener: (Book) -> Unit) {

        binding.tvNameBook.text = item.title ?: ""
        binding.tvGenre.text = item.genres?.map { it.name }.toString()
        binding.tvAuthor.text = item.authorName ?: ""

        itemView.setOnClickListener {
            listener.invoke(item)
        }
    }
}

