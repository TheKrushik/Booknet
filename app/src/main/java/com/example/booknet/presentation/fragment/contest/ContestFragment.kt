package com.example.booknet.presentation.fragment.contest

import android.os.Bundle
import android.view.View
import com.example.booknet.R
import com.example.booknet.databinding.FragmentContestBinding
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContestFragment : BaseFragment(R.layout.fragment_contest) {

    private val binding by viewBinding(FragmentContestBinding::bind)
    private val viewModel: ContestViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {

    }

    private fun setupObservers() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textContest.text = it
        }
    }
}