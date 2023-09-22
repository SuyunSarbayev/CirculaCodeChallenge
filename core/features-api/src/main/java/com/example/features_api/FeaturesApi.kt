package com.example.features_api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Extension function for every feature module route
 * it is needed for every feature to add itself to the NavHost
 */
interface FeaturesApi {
    val route: String

    fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
    )
}
