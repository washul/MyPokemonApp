package com.wsl.domain.di

import com.wsl.domain.pokemon.usecases.GetPokemonListUseCase
import com.wsl.domain.pokemon.usecases.GetPokemonUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetPokemonUseCase(get()) }
    factory { GetPokemonListUseCase(get()) }
}