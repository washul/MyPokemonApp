package com.wsl.data.pokemon.repository

import com.wsl.data.db.PokemonDataBase
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.utils.Failure
import com.wsl.utils.Result

class PokemonLocalDataSource(private val db: PokemonDataBase) {

    suspend fun getFavoritesPokemon(): Result<Failure, List<Pokemon>> {
        return try {
            Result.Success(db.pokemonDao().getAll())
        } catch (e: Throwable){
            e.printStackTrace()
            Result.Failure(
                Failure.ServerError(e)
            )
        }
    }


    suspend fun setFavorite(pokemon: Pokemon): Result<Failure, Boolean> {
        return try {
            db.pokemonDao().insert(pokemon)
            Result.Success(true)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Failure(
                Failure.ServerError(e)
            )
        }
    }

    suspend fun removeFavorite(id: Int): Result<Failure, Boolean> {
        return try {
            db.pokemonDao().delete(id)
            Result.Success(true)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Failure(
                Failure.ServerError(e)
            )
        }
    }

}