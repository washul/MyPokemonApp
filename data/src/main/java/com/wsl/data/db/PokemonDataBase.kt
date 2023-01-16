package com.wsl.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wsl.data.pokemon.dao.PokemonDao
import com.wsl.domain.pokemon.model.Pokemon

@Database(entities = [Pokemon::class], version = 1)
abstract class PokemonDataBase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

   companion object {

       fun createDBInstance(context: Context) =
           Room.databaseBuilder(
               context,
               PokemonDataBase::class.java,
               "database-name"
           ).build()

   }

}