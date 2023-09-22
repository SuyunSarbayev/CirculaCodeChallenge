package com.example.impl.ui

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.api.PokemonDetailFeatureApi

/**
 * Feature module entry point declaration, registerGraph implementation from extension
 * function declared in featureApi module, which add composable route with navArgument pokemonId
 * to pass login param for detail request
 */
class PokemonDetailFeatureImpl : PokemonDetailFeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(
            route,
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.StringType
                },
            ),
        ) {
            PokemonDetailScreen()
        }
    }
}
