package com.wsl.data.pokemon.repository

import com.wsl.data.db.PokemonDataBase
import com.wsl.data.utils.simpleRequest
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.utils.Failure
import com.wsl.utils.Result

class PokemonLocalDataSource(private val db: PokemonDataBase) {

    suspend fun isFavoritePokemon(id: Int): Result<Failure, Boolean> {
        return simpleRequest(
            db.pokemonDao().isFavorite(id),
            false
        )
    }

    suspend fun getFavoritesPokemon(): Result<Failure, List<Pokemon>> {
        return simpleRequest(
            db.pokemonDao().getAll(),
            listOf()
        )
    }


    suspend fun setFavorite(pokemon: Pokemon): Result<Failure, Unit> {
        return simpleRequest(
            db.pokemonDao().insert(pokemon),
            Unit
        )
    }

    suspend fun removeFavorite(id: Int): Result<Failure, Unit> {
        return simpleRequest(
            db.pokemonDao().delete(id),
            Unit
        )
    }

}