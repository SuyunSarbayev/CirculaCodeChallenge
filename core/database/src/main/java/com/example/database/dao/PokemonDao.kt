package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    fun initiateGetPokemons(): List<PokemonEntity>

    @Query("SELECT * FROM pokemons WHERE pokemonId IN (:pokemonIds)")
    fun initiateGetPokemonsById(pokemonIds: String): PokemonEntity

    @Insert
    fun initiateInsertPokemons(pokemons: List<PokemonEntity>)

    @Insert
    fun initiateInsertPokemon(pokemon: PokemonEntity)

    @Delete
    fun initiateDeletePokemon(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemons")
    fun initiateClearPokemons()
}
