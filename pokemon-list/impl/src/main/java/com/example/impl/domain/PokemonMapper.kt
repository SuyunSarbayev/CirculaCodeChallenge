package com.example.impl.domain

import com.example.api.data.Pokemons
import com.example.database.entity.PokemonEntity

fun Pokemons.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        pokemonId = this.id,
        name = this.name,
        imagePath = this.imagePath,
    )
}

fun PokemonEntity.toPokemon(): Pokemons {
    return Pokemons(
        id = this.pokemonId,
        name = this.name,
        imagePath = this.imagePath,
    )
}
