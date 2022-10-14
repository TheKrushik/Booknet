package com.example.booknet.presentation.fragment.settings

import android.os.Bundle
import android.view.View
import com.example.booknet.R
import com.example.booknet.databinding.FragmentSettingsBinding
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.presentation.fragment.library.LibraryViewModel
import com.example.booknet.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val binding by viewBinding(FragmentSettingsBinding::bind)
    private val viewModel: LibraryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {

    }

    private fun setupObservers() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textSettings.text = it
        }
    }
}