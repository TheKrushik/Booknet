package com.example.booknet.presentation.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.booknet.GraphMainDirections
import com.example.booknet.R
import com.example.booknet.util.snackbar

abstract class BaseFragment(@get:LayoutRes val layoutRes: Int) : Fragment(layoutRes) {

    protected val navController: NavController by lazy {
        Navigation.findNavController(requireActivity(), R.id.nav_host)
    }

    protected fun observeInternet(viewModel: BaseViewModel) {
        viewModel.noInternet.observe(viewLifecycleOwner) { noInternet ->
            if (noInternet) {
                navController.navigate(GraphMainDirections.actionGlobalNoInternetFragment())
                viewModel.onInternet()
            }
        }
    }

    protected fun observeSpinner(viewModel: BaseViewModel, progressBar: View) {
        viewModel.spinner.observe(viewLifecycleOwner) { value ->
            value.let { show ->
                progressBar.isVisible = show
            }
        }
    }

    protected fun observeSnackbar(viewModel: BaseViewModel, view: View) {
        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                view.snackbar(text)
                viewModel.onSnackbarHide()
            }
        }
    }
}
