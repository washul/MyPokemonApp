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

    /*
    * Api Data Source
    * */

    override suspend fun getPokemon(idOrName: String): Result<Failure, Pokemon>
        = pokemonApiDataSource.getPokemon(idOrName)

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<Failure, NamedApiResourceList>
        = pokemonApiDataSource.getPokemonList(limit, offset)


    /*
    * Local Data Source
    * */

    override suspend fun isFavoritePokemon(id: Int): Result<Failure, Boolean>
            = pokemonLocalDataSource.isFavoritePokemon(id)

    override suspend fun getFavoritesPokemon(): Result<Failure, List<Pokemon>>
            = pokemonLocalDataSource.getFavoritesPokemon()

    override suspend fun setFavorite(pokemon: Pokemon): Result<Failure, Unit>
        = pokemonLocalDataSource.setFavorite(pokemon)

    override suspend fun deleteFavorite(id: Int): Result<Failure, Unit>
        = pokemonLocalDataSource.removeFavorite(id)

}