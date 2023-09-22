package com.example.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.impl.domain.FeatureViewModel
import com.example.ui.state.UiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeatureScreen(
    navigatePokemonDetailScreen: (pokemon: String) -> Unit,
) {
    val viewModel = hiltViewModel<FeatureViewModel>()

    val pokemons = viewModel.pokemonState.collectAsState()

    val isRefreshing = pokemons.value is UiState.Loading

    val pullRefreshState =
        rememberPullRefreshState(isRefreshing, { viewModel.initiateRequestPokemons() })

    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Column {
            pokemons.let {
                when {
                    it.value is UiState.Loading -> {
                        PokemonsShimmer()
                    }

                    it.value is UiState.Success -> {
                        val pokemonsItems = (it.value as UiState.Success).data
                        if (pokemonsItems.isEmpty()) {
                            PokemonsEmpty { viewModel.initiateRequestPokemons() }
                        } else {
                            PokemonsList(pokemonsItems, navigatePokemonDetailScreen)
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter),
        )
    }
}
