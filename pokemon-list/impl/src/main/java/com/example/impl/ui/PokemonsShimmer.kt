package com.example.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.impl.animations.loadingShimmerBrush
import com.example.ui.dimens.Dimens

@Composable
fun PokemonsShimmer() {
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
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(128.dp),
            contentPadding = PaddingValues(Dimens.contentPagePadding),
            verticalItemSpacing = Dimens.contentPagePadding,
            horizontalArrangement = Arrangement.spacedBy(Dimens.contentPagePadding),
            content = {
                items(10) {
                    Row(
                        modifier = Modifier
                            .aspectRatio(Dimens.imagesAspectRatio)
                            .padding(all = Dimens.viewShimmerPaddingSmall),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                                .background(brush = loadingShimmerBrush()),
                        )
                    }
                }
            },
        )
    }
}