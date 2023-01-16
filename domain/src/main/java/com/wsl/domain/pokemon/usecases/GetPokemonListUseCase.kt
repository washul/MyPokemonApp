package com.wsl.domain.pokemon.usecases

import com.wsl.domain.ParamsUseCase
import com.wsl.domain.UseCase
import com.wsl.domain.pokemon.model.NamedApiResourceList
import com.wsl.domain.pokemon.repository.PokemonRepository
import com.wsl.utils.Failure
import com.wsl.utils.Result

class GetPokemonListUseCase (private val repository: PokemonRepository):
    ParamsUseCase<NamedApiResourceList, GetPokemonListUseCase.Params> {

        override suspend fun invoke(params: Params): Result<Failure, NamedApiResourceList>
                = repository.getPokemonList(params.limit, params.offset)

        data class Params(val limit: Int, val offset: Int)
}