package com.example.booknet.di

import com.example.booknet.data.api.ApiService
import com.example.booknet.data.db.AppDatabase
import com.example.booknet.data.repository.BooksRepositoryImpl
import com.example.booknet.data.repository.ReaderRepositoryImpl
import com.example.booknet.domain.repository.BooksRepository
import com.example.booknet.domain.repository.ReaderRepository
import org.koin.dsl.module

fun moduleRepository() = module {

    single<BooksRepository> {
        BooksRepositoryImpl(get<AppDatabase>(), get<ApiService>())
    }
    single<ReaderRepository> {
        ReaderRepositoryImpl(get<ApiService>())
    }

}