package com.example.booknet.presentation.fragment.list

import android.os.Bundle
import android.view.View
import com.example.booknet.R
import com.example.booknet.databinding.FragmentListBookBinding
import com.example.booknet.domain.model.Book
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.presentation.fragment.list.adapter.TabConfiguration
import com.example.booknet.presentation.fragment.list.adapter.TabsAdapter
import com.example.booknet.util.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListBookFragment : BaseFragment(R.layout.fragment_list_book) {

    private val binding by viewBinding(FragmentListBookBinding::bind)
    private val viewModel: ListBookViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupTabs() {
        val tabsAdapter = TabsAdapter(this)
        binding.viewpager.adapter = tabsAdapter
        TabLayoutMediator(binding.tabs, binding.viewpager, TabConfiguration()).attach()
    }

    private fun setupObservers() {
        observeInternet(viewModel)
        observeSpinner(viewModel, binding.pbProgress.root)
        observeSnackbar(viewModel, binding.root)

        viewModel.loadBooks()

        viewModel.books.observe(viewLifecycleOwner) { list: List<Book> ->
            if (!list.isNullOrEmpty()) {
                binding.pbProgress.root.visibility = View.GONE
                setupTabs()
            }
        }
    }
}