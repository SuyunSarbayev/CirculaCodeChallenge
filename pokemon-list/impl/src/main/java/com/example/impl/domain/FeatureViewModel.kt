package com.example.impl.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.Pokemons
import com.example.database.repository.PokemonsDatabaseRepositoryImpl
import com.example.impl.repository.FeatureRepositoryImpl
import com.example.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val featureRepository: FeatureRepositoryImpl,
    private val pokemonUpdateUseCase: PokemonUpdateUseCase,
    private val databaseRepositoryImpl: PokemonsDatabaseRepositoryImpl,
) : ViewModel() {

    /**
     * MutableState for internal usage inside class
     */
    private val _pokemonState = MutableStateFlow<UiState<List<Pokemons>>>(UiState.Loading)

    /**
     * Public declaration for outside usage and testing
     */
    val pokemonState: StateFlow<UiState<List<Pokemons>>> = _pokemonState

    init {
        initiateRequestPokemons()
    }

    private fun initiateRequestCachedPokemons() {
        viewModelScope.launch {
            flow { emit(databaseRepositoryImpl.initiateRequestPokemons()) }
                .onStart { _pokemonState.value = UiState.Loading }
                .catch { _pokemonState.value = UiState.Error(it) }
                .map { it.map { pokemon -> pokemon.toPokemon() } }
                .collect { _pokemonState.value = UiState.Success(it) }
        }
    }

    fun initiateRequestPokemons() {
        viewModelScope.launch {
            featureRepository.pokemonsRequest()
                .onStart { _pokemonState.value = UiState.Loading }
                .catch { initiateRequestCachedPokemons() }
                .map {
                    if (pokemonUpdateUseCase.initiateCheckLastUpdateTime()) {
                        pokemonUpdateUseCase.initiateUpdateTime()
                        databaseRepositoryImpl.initiateClearPokemons()
                        it.pokemons.forEach { pokemon ->
                            databaseRepositoryImpl.initiateInsertPokemon(pokemon.toPokemonEntity())
                        }
                    }
                    it.pokemons
                }
                .collect { _pokemonState.value = UiState.Success(it) }
        }
    }
}
