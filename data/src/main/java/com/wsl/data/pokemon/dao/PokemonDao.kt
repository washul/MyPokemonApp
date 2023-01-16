package com.wsl.data.pokemon.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wsl.domain.pokemon.model.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getAll(): List<Pokemon>

    @Insert
    suspend fun insert(vararg users: Pokemon)

    @Query("DELETE FROM pokemon WHERE ID == :id")
    suspend fun delete(id: Int): List<Pokemon>

}