package com.example.booknet.presentation.fragment.reader

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.booknet.R
import com.example.booknet.databinding.FragmentReaderBinding
import com.example.booknet.domain.model.BookChapters
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.presentation.fragment.reader.adapter.ChapterAdapter
import com.example.booknet.util.snackbar
import com.example.booknet.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReaderFragment : BaseFragment(R.layout.fragment_reader) {

    private val binding by viewBinding(FragmentReaderBinding::bind)
    private val viewModel: ReaderViewModel by viewModel()
    private val args: ReaderFragmentArgs by navArgs()
    private lateinit var adapter: ChapterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        setupRecycler()
    }

    private fun setupRecycler() {
        adapter = ChapterAdapter{ item: BookChapters ->
            binding.root.snackbar("ID Chapter: ${item.id}")
        }
        binding.rvChapters.adapter = adapter
    }

    private fun setupObservers() {
        observeInternet(viewModel)
        observeSpinner(viewModel, binding.pbProgress.root)
        observeSnackbar(viewModel, binding.root)

        viewModel.loadBookChapters(args.argBook.id)

        viewModel.bookChapters.observe(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) {
                adapter.updateData(list)
                binding.pbProgress.root.visibility = View.GONE
            }
        }
    }
}