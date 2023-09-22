package com.example.codechallenge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.features_api.register
import com.example.navigation.NavigationProvider

@Composable
fun CodeChallengeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationProvider.feature().route,
    ) {
        register(
            feature = NavigationProvider.feature(),
            navHostController = navController,
            modifier = modifier,
        )

        register(
            feature = NavigationProvider.pokemonDetails(),
            navHostController = navController,
            modifier = modifier,
        )
    }
}
