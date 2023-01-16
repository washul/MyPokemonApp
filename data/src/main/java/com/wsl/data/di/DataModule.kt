package com.wsl.data.di

import com.wsl.data.*
import com.wsl.data.db.PokemonDataBase
import com.wsl.data.pokemon.repository.PokemonApiDataSource
import com.wsl.data.pokemon.repository.PokemonLocalDataSource
import com.wsl.data.pokemon.repository.PokemonRepositoryImpl
import com.wsl.domain.pokemon.repository.PokemonRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val RETROFIT_CLIENT = "RetrofitClient"

val dataModule = module {


    single<PokemonRepository> { PokemonRepositoryImpl( get(), get() ) }
    single { PokemonApiDataSource(get()) }
    single { PokemonLocalDataSource(get()) }

    //DB
    single { PokemonDataBase.createDBInstance(androidApplication().applicationContext) }

    //Retrofit
    factory { provideService( get(named(RETROFIT_CLIENT)) ) }
    single(named(RETROFIT_CLIENT)) { provideRetrofit( get(named("plainOkHttpClient")), get() ) }
    single { provideGsonInstance() }

    factory(named("plainOkHttpClient")) { providePlainOkHttpClient() }
    factory(named("sessionCookieJar")) { provideCookieJar() }
}