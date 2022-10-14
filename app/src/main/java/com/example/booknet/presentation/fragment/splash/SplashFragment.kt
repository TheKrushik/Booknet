package com.example.booknet.presentation.fragment.splash

import android.os.Bundle
import android.view.View
import com.example.booknet.R
import com.example.booknet.databinding.FragmentSplashBinding
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.navigateToAuthOrMain()
        observeInternet(viewModel)
        observeSnackbar(viewModel, binding.root)

        viewModel.startAuth.observe(viewLifecycleOwner) {
            if (it)
                navController.navigate(SplashFragmentDirections.actionToSignInFragment())
        }

        viewModel.startClient.observe(viewLifecycleOwner) {
            if (it)
                navController.navigate(SplashFragmentDirections.actionNavSplashToNavListBook())
        }
    }

}