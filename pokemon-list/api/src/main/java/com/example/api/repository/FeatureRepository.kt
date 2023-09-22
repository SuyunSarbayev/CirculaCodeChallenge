package com.example.api.repository

import com.example.api.data.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface FeatureRepository {
    fun pokemonsRequest(): Flow<PokemonResponse>
}