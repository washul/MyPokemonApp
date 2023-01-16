package com.wsl.data.pokemon.mappers

import com.wsl.domain.pokemon.model.NamedApiResource
import com.wsl.domain.pokemon.model.NamedApiResourceList
import com.wsl.domain.pokemon.model.Pokemon

fun makeBasicPokemonMapper(): (Pokemon) -> Pokemon = {
    it
}

fun makeBasicPokemonListMapper(): (NamedApiResourceList) -> NamedApiResourceList = {
    it
}