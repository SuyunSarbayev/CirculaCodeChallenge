package com.example.codechallenge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.codechallenge.navigation.CodeChallengeNavGraph
import com.example.codechallenge.ui.CodeChallengeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootView() {
    CodeChallengeTheme {
        val navController = rememberNavController()
        val canPopNavigation = remember { mutableStateOf(false) }

        navController.addOnDestinationChangedListener { controller, _, _ ->
            canPopNavigation.value = controller.previousBackStackEntry != null
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    navigationIcon = {
                        if (canPopNavigation.value) {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Filled.ArrowBack, "")
                            }
                        }
                    },
                )
            },
            content = { innerPaddingModifier ->
                Box(Modifier.padding(innerPaddingModifier)) {
                    CodeChallengeNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPaddingModifier),
                    )
                }
            },
        )
    }
}
