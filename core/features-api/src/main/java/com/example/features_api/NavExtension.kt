package com.example.features_api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Being used in app module to trigger every module to call registerGraph extension
 * declared above for every feature module to add itself to navGraph
 */
fun NavGraphBuilder.register(
    feature: FeaturesApi,
    navHostController: NavHostController,
    modifier: Modifier,
) {
    feature.registerGraph(
        navHostController = navHostController,
        navGraphBuilder = this,
        modifier = modifier,
    )
}
