package com.example.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.impl.animations.loadingShimmerBrush
import com.example.impl.domain.PokemonDetailViewModel
import com.example.ui.dimens.Dimens
import com.example.ui.state.UiState

@Composable
fun PokemonDetailScreen() {
    val viewModel = hiltViewModel<PokemonDetailViewModel>()

    val pokemonDetailState = viewModel.pokemonDetailState.collectAsState()

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        when (pokemonDetailState.value) {
            is UiState.Loading -> PokemonDetailShimmer()
            is UiState.Success -> {
                val data = (pokemonDetailState.value as UiState.Success).data
                Column {
                    PokemonName(data?.name)
                    PokemonImage(data?.imagePath)
                }
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun PokemonName(pokemonName: String?) {
    Text(
        modifier = Modifier.padding(
            top = Dimens.viewVerticalPadding,
            start = Dimens.contentPagePadding,
            end = Dimens.contentPagePadding,
            bottom = Dimens.viewVerticalPadding,
        ).fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.textSizeLarge,
        text = pokemonName ?: "",
    )
}

@Composable
fun PokemonImage(largeImageURL: String?) {
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(largeImageURL)
                .allowHardware(false)
                .build(),
            error = ColorPainter(Color.LightGray),
            onSuccess = {},
            onError = {},
        ),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    )
}

@Composable
fun PokemonDetailShimmer() {
    Box {
        LazyColumn(Modifier.fillMaxHeight()) {
            item { PokemonNameShimmer() }
            item { PokemonBannerShimmer() }
        }
    }
}

@Composable
fun PokemonBannerShimmer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(brush = loadingShimmerBrush()),
    )
}

@Composable
fun PokemonNameShimmer() {
    Spacer(
        modifier = Modifier
            .width(200.dp)
            .height(40.dp)
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 2.dp)
            .background(shape = RoundedCornerShape(16.dp), brush = loadingShimmerBrush()),
    )
}
