package com.example.impl.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.Pokemons
import com.example.database.repository.PokemonsDatabaseRepository
import com.example.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for pokemon detail provided with HiltViewModel annotation
 */
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val pokemonDatabase: PokemonsDatabaseRepository,
) : ViewModel() {

    private val _pokemonDetailState = MutableStateFlow<UiState<Pokemons>>(UiState.Loading)

    val pokemonDetailState: StateFlow<UiState<Pokemons?>> = _pokemonDetailState

    val pokemonId = savedStateHandle.get<String>("pokemonId")

    init {
        initiateRequestCachedPokemonDetail()
    }

    private fun initiateRequestCachedPokemonDetail() {
        viewModelScope.launch {
            flow { emit(pokemonDatabase.initiateRequestPokemon(pokemonId!!)) }
                .map { it.toPokemon() }
                .catch { _pokemonDetailState.value = UiState.Error(it) }
                .collect { _pokemonDetailState.value = UiState.Success(it) }
        }
    }
}
