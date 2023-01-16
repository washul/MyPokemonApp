package com.wsl.mypokemonapp.di

import com.wsl.mypokemonapp.layout.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), get()) }
}