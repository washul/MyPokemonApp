package com.wsl.data.pokemon.repository

import com.wsl.domain.pokemon.model.NamedApiResourceList
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.domain.pokemon.repository.PokemonRepository
import com.wsl.utils.Failure
import com.wsl.utils.Result

class PokemonRepositoryImpl(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonApiDataSource: PokemonApiDataSource
): PokemonRepository {

    override suspend fun getPokemon(idOrName: String): Result<Failure, Pokemon>
        = pokemonApiDataSource.getPokemon(idOrName)

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<Failure, NamedApiResourceList>
        = pokemonApiDataSource.getPokemonList(limit, offset)


}