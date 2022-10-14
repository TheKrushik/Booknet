package com.example.booknet.di

import com.example.booknet.data.db.AppDatabase
import com.example.booknet.data.db.getDatabase
import com.example.booknet.data.prefs.SharedPrefsUser
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun moduleStorage() = module {

    single { SharedPrefsUser(androidContext()) }

    single { getDatabase(androidContext()) }
    single { get<AppDatabase>().booksDao() }
}