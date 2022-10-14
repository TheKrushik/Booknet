package com.example.booknet.di

import com.example.booknet.data.prefs.SharedPrefsUser
import com.example.booknet.domain.repository.BooksRepository
import com.example.booknet.domain.repository.ReaderRepository
import com.example.booknet.presentation.activity.main.MainViewModel
import com.example.booknet.presentation.fragment.auth.AuthViewModel
import com.example.booknet.presentation.fragment.contest.ContestViewModel
import com.example.booknet.presentation.fragment.library.LibraryViewModel
import com.example.booknet.presentation.fragment.list.ListBookViewModel
import com.example.booknet.presentation.fragment.notifications.NotificationsViewModel
import com.example.booknet.presentation.fragment.reader.ReaderViewModel
import com.example.booknet.presentation.fragment.settings.SettingsViewModel
import com.example.booknet.presentation.fragment.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun moduleViewModel() = module {

    viewModel { MainViewModel() }
    viewModel { SplashViewModel(get<SharedPrefsUser>()) }
    viewModel { AuthViewModel(get<SharedPrefsUser>()) }
    viewModel { ListBookViewModel(get<BooksRepository>()) }
    viewModel { ReaderViewModel(get<ReaderRepository>()) }
    viewModel { LibraryViewModel() }
    viewModel { NotificationsViewModel() }
    viewModel { ContestViewModel() }
    viewModel { SettingsViewModel() }

}