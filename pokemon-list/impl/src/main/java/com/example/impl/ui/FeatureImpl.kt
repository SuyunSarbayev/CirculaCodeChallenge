package com.example.impl.ui

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.api.ui.FeatureApi

/**
 * Pokemon list feature entry point which contains implementation of graph registering
 * function extension declared in feature api module, it must add composable route to the NavGraph
 */
class FeatureImpl : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(route) {
            FeatureScreen {
                navHostController.navigate("pokemon-detail?pokemonId=$it")
            }
        }
    }
}
