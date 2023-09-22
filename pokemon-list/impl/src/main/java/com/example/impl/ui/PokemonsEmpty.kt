package com.example.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.impl.R
import com.example.ui.dimens.Dimens

@Composable
fun PokemonsEmpty(update: () -> Unit) {
    Box(Modifier.fillMaxWidth().fillMaxHeight()) {
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(Dimens.contentPagePadding),
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp),
                tint = MaterialTheme.colorScheme.onSurface,
                imageVector = Icons.Default.Search,
                contentDescription = "",
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.pokemons_list_search_empty_title),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = stringResource(id = R.string.pokemons_list_search_empty_subtitle),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.contentPagePadding),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                minLines = 2,
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(160.dp),
                onClick = { update.invoke() },
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(id = R.string.pokemons_list_search_reload_page),
                )
            }
        }
    }
}

@Composable
@Preview
fun PokemonsEmptyPreview() {
    PokemonsEmpty({})
}
