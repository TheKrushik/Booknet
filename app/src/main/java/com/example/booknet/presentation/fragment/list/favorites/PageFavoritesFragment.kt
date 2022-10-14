package com.example.booknet.presentation.fragment.list.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.booknet.R
import com.example.booknet.databinding.FragmentPageBooksBinding
import com.example.booknet.domain.model.Book
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.presentation.fragment.list.ListBookFragmentDirections
import com.example.booknet.presentation.fragment.list.ListBookViewModel
import com.example.booknet.presentation.fragment.list.adapter.BookAdapter
import com.example.booknet.util.safeNavigate
import com.example.booknet.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PageFavoritesFragment : BaseFragment(R.layout.fragment_page_books) {

    private val binding by viewBinding(FragmentPageBooksBinding::bind)
    private val viewModel: ListBookViewModel by sharedViewModel()
    private lateinit var adapter: BookAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecycler()
    }

    private fun setupRecycler() {
        adapter = BookAdapter { item: Book ->
            val directions = ListBookFragmentDirections.actionToNavReader(item)
            navController.safeNavigate(directions)
        }
        binding.rvBooks.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.favorites.observe(viewLifecycleOwner) { list: List<Book> ->
            if (!list.isNullOrEmpty()) {
                adapter.updateData(list)
            }
        }
    }

    companion object {
        fun newInstance(): Fragment = PageFavoritesFragment()
    }
}