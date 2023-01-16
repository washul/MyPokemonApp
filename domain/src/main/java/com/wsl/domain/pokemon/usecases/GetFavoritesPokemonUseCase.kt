package com.wsl.domain.pokemon.usecases

import com.wsl.domain.UseCase
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.domain.pokemon.repository.PokemonRepository
import com.wsl.utils.Failure
import com.wsl.utils.Result

class GetFavoritesPokemonUseCase(private val repository: PokemonRepository):
    UseCase<List<Pokemon>> {

    override suspend fun invoke(): Result<Failure, List<Pokemon>>
        = repository.getFavoritesPokemon()
}