package com.example.impl.repository

import com.example.api.FeatureApiService
import com.example.api.data.PokemonResponse
import com.example.api.repository.FeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeatureRepositoryImpl @Inject constructor(private val featureApiService: FeatureApiService) :
    FeatureRepository {
    override fun pokemonsRequest(): Flow<PokemonResponse> {
        return flow { emit(featureApiService.pokemons()) }
    }
}
