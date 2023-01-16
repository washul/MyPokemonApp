package com.wsl.domain.pokemon.usecases

import com.wsl.domain.ParamsUseCase
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.domain.pokemon.repository.PokemonRepository
import com.wsl.utils.Failure
import com.wsl.utils.Result

class GetPokemonUseCase(private val repository: PokemonRepository):
    ParamsUseCase<Pokemon, GetPokemonUseCase.Params> {

    override suspend fun invoke(params: Params): Result<Failure, Pokemon>
            = repository.getPokemon(params.idOrName)


    data class Params(val idOrName: String)
}