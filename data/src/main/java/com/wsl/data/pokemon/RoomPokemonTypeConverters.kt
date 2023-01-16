package com.wsl.data.pokemon

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wsl.domain.pokemon.model.*

class RoomPokemonTypeConverters {

    /**
     * Pokemon
     * */
    @TypeConverter
    fun toPokemon(value: String): Pokemon
            = Gson().fromJson(value, Pokemon::class.java)

    @TypeConverter
    fun namedPokemonToString(namedApiResource: Pokemon): String
            = Gson().toJson(namedApiResource)

    /**
     * NamedApiResource
     * */
    @TypeConverter
    fun toNamedApiResource(value: String): NamedApiResource
        = Gson().fromJson(value, NamedApiResource::class.java)

    @TypeConverter
    fun namedApiResourceToString(namedApiResource: NamedApiResource): String?
        = Gson().toJson(namedApiResource)

    @TypeConverter
    fun toListNamedApiResource(value: String): List<NamedApiResource>
            = Gson().fromJson(value, Array<NamedApiResource>::class.java).toList()

    @TypeConverter
    fun listNamedApiResourceToString(listNamedApiResource: List<NamedApiResource>): String
            = Gson().toJson(listNamedApiResource)

    /**
     * PokemonAbility
     * */
    @TypeConverter
    fun toListPokemonAbility(value: String): List<PokemonAbility>
            = Gson().fromJson(value, Array<PokemonAbility>::class.java).toList()

    @TypeConverter
    fun listPokemonAbilityToString(value: List<PokemonAbility>): String
            = Gson().toJson(value)

    /**
     * VersionGameIndex
     * */
    @TypeConverter
    fun toListVersionGameIndex(value: String): List<VersionGameIndex>
            = Gson().fromJson(value, Array<VersionGameIndex>::class.java).toList()

    @TypeConverter
    fun listVersionGameIndexToString(value: List<VersionGameIndex>): String
            = Gson().toJson(value)

    /**
     * PokemonHeldItem
     * */
    @TypeConverter
    fun toListPokemonHeldItem(value: String): List<PokemonHeldItem>
            = Gson().fromJson(value, Array<PokemonHeldItem>::class.java).toList()

    @TypeConverter
    fun listPokemonHeldItemToString(value: List<PokemonHeldItem>): String
            = Gson().toJson(value)

    @TypeConverter
    fun toListPokemonHeldItemVersion(value: String): List<PokemonHeldItemVersion>
            = Gson().fromJson(value, Array<PokemonHeldItemVersion>::class.java).toList()

    @TypeConverter
    fun listPokemonHeldItemVersionToString(value: List<PokemonHeldItemVersion>): String
            = Gson().toJson(value)

    /**
     * PokemonMove
     * */
    @TypeConverter
    fun toListPokemonMove(value: String): List<PokemonMove>
            = Gson().fromJson(value, Array<PokemonMove>::class.java).toList()

    @TypeConverter
    fun listPokemonMoveToString(value: List<PokemonMove>): String
            = Gson().toJson(value)

    @TypeConverter
    fun toListPokemonMoveVersion(value: String): List<PokemonMoveVersion>
            = Gson().fromJson(value, Array<PokemonMoveVersion>::class.java).toList()

    @TypeConverter
    fun listPokemonMoveVersionToString(value: List<PokemonMoveVersion>): String
            = Gson().toJson(value)

    /**
     * PokemonStat
     * */
    @TypeConverter
    fun toListPokemonStat(value: String): List<PokemonStat>
            = Gson().fromJson(value, Array<PokemonStat>::class.java).toList()

    @TypeConverter
    fun listPokemonStatToString(value: List<PokemonStat>): String
            = Gson().toJson(value)

    /**
     * PokemonType
     * */
    @TypeConverter
    fun toListPokemonType(value: String): List<PokemonType>
            = Gson().fromJson(value, Array<PokemonType>::class.java).toList()

    @TypeConverter
    fun listPokemonTypeToString(value: List<PokemonType>): String
            = Gson().toJson(value)

    /**
     * PokemonSprites
     * */
    @TypeConverter
    fun toListPokemonSprites(value: String): PokemonSprites
            = Gson().fromJson(value, PokemonSprites::class.java)

    @TypeConverter
    fun listPokemonSpritesToString(value: PokemonSprites): String?
            = Gson().toJson(value)
}
