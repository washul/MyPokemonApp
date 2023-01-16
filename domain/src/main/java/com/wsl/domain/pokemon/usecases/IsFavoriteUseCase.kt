package com.wsl.domain.pokemon.usecases

import com.wsl.domain.ParamsUseCase
import com.wsl.domain.pokemon.repository.PokemonRepository
import com.wsl.utils.Failure
import com.wsl.utils.Result

class IsFavoriteUseCase (private val repository: PokemonRepository):
    ParamsUseCase<Boolean, IsFavoriteUseCase.Params> {

    override suspend fun invoke(params: Params): Result<Failure, Boolean>
            = repository.isFavoritePokemon(params.id)

    data class Params(val id: Int)
}