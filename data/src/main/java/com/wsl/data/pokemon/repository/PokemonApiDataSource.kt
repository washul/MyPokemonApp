package com.wsl.data.pokemon.repository

import com.wsl.data.pokemon.mappers.makeBasicPokemonListMapper
import com.wsl.data.pokemon.mappers.makeBasicPokemonMapper
import com.wsl.data.pokemon.service.PokemonApiService
import com.wsl.data.utils.Request
import com.wsl.domain.pokemon.model.NamedApiResource
import com.wsl.domain.pokemon.model.NamedApiResourceList
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.utils.Failure
import com.wsl.utils.Result
import com.wsl.utils.map

class PokemonApiDataSource(private val apiService: PokemonApiService) {

    suspend fun getPokemon(idOrName: String): Result<Failure, Pokemon> {
        return Request(
            {apiService.getPokemon(idOrName)},
            Pokemon()
        ).map(makeBasicPokemonMapper())
    }

    suspend fun getPokemonList(limit: Int, offset: Int): Result<Failure, NamedApiResourceList> {
        return Request(
                {apiService.getPokemonList(limit, offset)},
                NamedApiResourceList()
            ).map(makeBasicPokemonListMapper())
    }

}