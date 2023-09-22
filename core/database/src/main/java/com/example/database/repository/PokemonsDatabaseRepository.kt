package com.example.database.repository

import com.example.database.entity.PokemonEntity

interface PokemonsDatabaseRepository {
    suspend fun initiateInsertPokemons(pokemonsEntity: List<PokemonEntity>)

    suspend fun initiateInsertPokemon(pokemonEntity: PokemonEntity)

    suspend fun initiateRequestPokemons(): List<PokemonEntity>

    suspend fun initiateRequestPokemon(pokemonId: String): PokemonEntity

    suspend fun initiateClearPokemons()
}