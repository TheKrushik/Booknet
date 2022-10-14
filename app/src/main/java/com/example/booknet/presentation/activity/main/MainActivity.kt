package com.example.booknet.presentation.activity.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.booknet.R
import com.example.booknet.databinding.ActivityMainBinding
import com.example.booknet.presentation.base.BaseActivity
import com.example.booknet.util.viewBinding
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_library,
                R.id.nav_list_book,
                R.id.nav_notifications,
                R.id.nav_contest,
                R.id.nav_settings,
                R.id.nav_auth,
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupObservers() {
        viewModel.spinner.observe(this) { value ->
            value.let { show ->
                binding.appBarMain.contentMain.pbProgress.root.isVisible = show
            }
        }
    }
}