package com.wsl.domain.pokemon.repository

import com.wsl.domain.pokemon.model.NamedApiResourceList
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.utils.Failure
import com.wsl.utils.Result

interface PokemonRepository {

    suspend fun getPokemon(idOrName: String): Result<Failure, Pokemon>

    suspend fun getPokemonList(limit: Int, offset: Int): Result<Failure, NamedApiResourceList>

}