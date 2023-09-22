package com.example.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.api.data.Pokemons
import com.example.ui.dimens.Dimens

@Composable
fun PokemonsList(pokemons: List<Pokemons>?, navigatePokemonDetails: (pokemon: String) -> Unit) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = state,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300)),
    ) {
        pokemons.let {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(128.dp),
                contentPadding = PaddingValues(Dimens.contentPagePadding),
                verticalItemSpacing = Dimens.contentPagePadding,
                horizontalArrangement = Arrangement.spacedBy(Dimens.contentPagePadding),
            ) {
                items(pokemons?.size ?: 0) {
                    pokemons!![it].let { pokemon ->
                        Card(
                            shape = MaterialTheme.shapes.medium,
                            elevation = 10.dp,
                            modifier = Modifier.clickable { navigatePokemonDetails(pokemon.id) },
                        ) {
                            PokemonGridItem(pokemon)
                        }
                    }
                }
            }
        }
    }
}
