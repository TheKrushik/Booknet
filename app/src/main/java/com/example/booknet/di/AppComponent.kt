package com.example.booknet.di

import org.koin.core.module.Module

val appComponent = listOf<Module>(
    moduleStorage(),
    moduleNetwork(),
    moduleRepository(),
    moduleViewModel()
)