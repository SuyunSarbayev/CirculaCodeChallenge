package com.example.api

import com.example.features_api.FeaturesApi

/**
 * Feature api for pokemon detail page, contains route for navigation component
 * and pokemon id as a parameter to pass as argument to get detailed information
 */
interface PokemonDetailFeatureApi : FeaturesApi {
    override val route: String
        get() = "pokemon-detail?pokemonId={pokemonId}"
}
