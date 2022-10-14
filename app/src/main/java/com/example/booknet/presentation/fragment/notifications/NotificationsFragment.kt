package com.example.booknet.presentation.fragment.notifications

import android.os.Bundle
import android.view.View
import com.example.booknet.R
import com.example.booknet.databinding.FragmentNotificationsBinding
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : BaseFragment(R.layout.fragment_notifications) {

    private val binding by viewBinding(FragmentNotificationsBinding::bind)
    private val viewModel: NotificationsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {

    }

    private fun setupObservers() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textNotifications.text = it
        }
    }
}