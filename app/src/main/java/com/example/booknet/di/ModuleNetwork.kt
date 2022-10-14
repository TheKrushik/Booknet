package com.example.booknet.di

import com.example.booknet.data.api.getRestApi
import com.example.booknet.data.prefs.SharedPrefsUser
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun moduleNetwork() = module {

    single { getRestApi(androidContext(), get<SharedPrefsUser>()) }
}