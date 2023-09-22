package com.example.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.api.data.Pokemons
import com.example.ui.dimens.Dimens

@Composable
fun PokemonGridItem(pokemon: Pokemons) {
    Column(
        Modifier.background(color = MaterialTheme.colorScheme.onSecondary),
    ) {
        Image(pokemon)
        Name(pokemon)
    }
}

@Composable
fun Image(item: Pokemons) {
    androidx.compose.foundation.Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imagePath)
                .allowHardware(false)
                .build(),
            error = ColorPainter(Color.LightGray),
            onSuccess = {},
            onError = {},
        ),
        contentDescription = null,
        modifier = Modifier
            .aspectRatio(Dimens.imagesAspectRatio)
            .clip(MaterialTheme.shapes.medium),
    )
}

@Composable
fun Name(item: Pokemons) {
    Text(
        modifier = Modifier.padding(
            top = Dimens.viewVerticalPadding,
            start = Dimens.contentPagePadding,
            end = Dimens.contentPagePadding,
            bottom = Dimens.viewVerticalPadding,
        ),
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = Dimens.textSizeLarge,
        text = item.name,
    )
}
