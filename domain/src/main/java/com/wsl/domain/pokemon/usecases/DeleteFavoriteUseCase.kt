package com.wsl.domain.pokemon.usecases

import com.wsl.domain.ParamsUseCase
import com.wsl.domain.pokemon.repository.PokemonRepository
import com.wsl.utils.Failure
import com.wsl.utils.Result

class DeleteFavoriteUseCase (private val repository: PokemonRepository):
    ParamsUseCase<Unit, DeleteFavoriteUseCase.Params> {

    override suspend fun invoke(params: Params): Result<Failure, Unit>
            = repository.deleteFavorite(params.id)

    data class Params(val id: Int)
}