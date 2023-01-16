package com.wsl.data.pokemon.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wsl.domain.pokemon.model.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getAll(): List<Pokemon>

    @Query("SELECT isFavorite FROM pokemon WHERE id == :id")
    suspend fun isFavorite(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg users: Pokemon)

    @Query("DELETE FROM pokemon WHERE ID == :id")
    suspend fun delete(id: Int)

}