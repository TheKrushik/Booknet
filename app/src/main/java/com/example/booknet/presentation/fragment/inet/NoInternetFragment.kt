package com.example.booknet.presentation.fragment.inet

import android.os.Bundle
import android.view.View
import com.example.booknet.R
import com.example.booknet.databinding.FragmentNoInternetBinding
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.util.viewBinding

class NoInternetFragment : BaseFragment(R.layout.fragment_no_internet) {

    private val binding by viewBinding(FragmentNoInternetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClick()
    }

    private fun setupOnClick() {
        binding.btnRefresh.setOnClickListener {
            navController.navigateUp()
        }
    }
}