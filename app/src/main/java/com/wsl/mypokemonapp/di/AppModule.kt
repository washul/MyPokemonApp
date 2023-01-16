package com.wsl.mypokemonapp.di

import com.wsl.mypokemonapp.HomeSharedViewModel
import com.wsl.mypokemonapp.layout.favorites.FavoriteViewModel
import com.wsl.mypokemonapp.layout.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel { HomeSharedViewModel() }
    viewModel { FavoriteViewModel() }
}