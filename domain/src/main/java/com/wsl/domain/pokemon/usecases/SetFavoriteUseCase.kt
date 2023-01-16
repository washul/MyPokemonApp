package com.wsl.domain.pokemon.usecases

import com.wsl.domain.ParamsUseCase
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.domain.pokemon.repository.PokemonRepository
import com.wsl.utils.Failure
import com.wsl.utils.Result

class SetFavoriteUseCase(private val repository: PokemonRepository):
    ParamsUseCase<Unit, SetFavoriteUseCase.Params> {

    override suspend fun invoke(params: Params): Result<Failure, Unit>
            = repository.setFavorite(params.pokemon)

    data class Params(val pokemon: Pokemon)
}