package com.example.booknet.presentation.fragment.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.booknet.R
import com.example.booknet.databinding.FragmentAuthBinding
import com.example.booknet.presentation.base.BaseFragment
import com.example.booknet.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment(R.layout.fragment_auth) {

    private val binding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel: AuthViewModel by viewModel()
    private val args: AuthFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupOnClick()
        setupObservers()
    }

    private fun setupViews() {

    }

    private fun setupOnClick() {
        binding.btnEnter.setOnClickListener {
            viewModel.login(true)
        }
    }

    private fun setupObservers() {
        viewModel.login(args.isLogin)

        viewModel.isLogin.observe(viewLifecycleOwner) { isLogin ->
            if (isLogin)
                navController.navigate(AuthFragmentDirections.actionToNavListBook())
        }
    }

}