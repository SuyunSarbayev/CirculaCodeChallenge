package com.example.api.data

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("pokemons") var pokemons: ArrayList<Pokemons> = arrayListOf(),
)

data class Pokemons(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("imagePath") var imagePath: String,
)
