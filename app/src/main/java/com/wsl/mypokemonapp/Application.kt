package com.wsl.mypokemonapp

import android.app.Application
import com.wsl.data.di.dataModule
import com.wsl.domain.di.domainModule
import com.wsl.mypokemonapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //Declare context application
            androidContext(this@PokemonApplication)
            //Declare modules
            modules(
                appModule,
                dataModule,
                domainModule
            )
        }
    }

}