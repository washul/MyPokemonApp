package com.wsl.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wsl.data.db.PokemonDataBase.Companion.DATABASE_VERSION
import com.wsl.data.pokemon.RoomPokemonTypeConverters
import com.wsl.data.pokemon.dao.PokemonDao
import com.wsl.domain.pokemon.model.Pokemon

@Database(
    entities = [Pokemon::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(RoomPokemonTypeConverters::class)
abstract class PokemonDataBase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {

        internal const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "pokemon-database"

        private lateinit var INSTANCE: PokemonDataBase
        fun getInstance( context: Context ): PokemonDataBase {

            if (!::INSTANCE.isInitialized){

                synchronized(PokemonDataBase::class){

                    INSTANCE = Room.databaseBuilder( context.applicationContext, PokemonDataBase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()

                }

            }

            return INSTANCE

        }

    }

}