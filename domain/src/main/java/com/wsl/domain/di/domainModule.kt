package com.wsl.domain.di

import com.wsl.domain.pokemon.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetPokemonUseCase(get()) }
    factory { GetPokemonListUseCase(get()) }
    factory { GetFavoritesPokemonUseCase(get()) }
    factory { SetFavoriteUseCase(get()) }
    factory { DeleteFavoriteUseCase(get()) }
    factory { IsFavoriteUseCase(get()) }
}