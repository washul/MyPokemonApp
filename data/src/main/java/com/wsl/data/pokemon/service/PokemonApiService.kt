package com.wsl.data.pokemon.service

import com.wsl.domain.pokemon.model.NamedApiResourceList
import com.wsl.domain.pokemon.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<NamedApiResourceList>

    @GET("pokemon/{id}/")
    suspend fun getPokemon(
        @Path("id") idOrName: String
    ): Response<Pokemon>


}