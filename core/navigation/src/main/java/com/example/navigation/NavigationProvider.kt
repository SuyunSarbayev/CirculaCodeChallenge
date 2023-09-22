package com.example.navigation

import com.example.api.PokemonDetailFeatureApi
import com.example.api.ui.FeatureApi

/**
 * Navigation provider for feature declarations, it is being used in app module
 * for nav graph to access routes, navigation knows about all feature modules and only dependent
 * from them
 */
object NavigationProvider {
    private lateinit var feature: FeatureApi

    private lateinit var pokemonDetails: PokemonDetailFeatureApi

    fun provideNavigations(
        feature: FeatureApi,
        pokemonDetails: PokemonDetailFeatureApi,
    ) {
        NavigationProvider.feature = feature
        NavigationProvider.pokemonDetails = pokemonDetails
    }

    fun feature(): FeatureApi = feature

    fun pokemonDetails(): PokemonDetailFeatureApi = pokemonDetails
}
