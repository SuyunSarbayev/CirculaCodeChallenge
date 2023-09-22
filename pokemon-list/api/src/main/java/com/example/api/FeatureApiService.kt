package com.example.api

import com.example.api.data.PokemonResponse
import retrofit2.http.GET

interface FeatureApiService {
    @GET("pokemon")
    suspend fun pokemons(): PokemonResponse
}