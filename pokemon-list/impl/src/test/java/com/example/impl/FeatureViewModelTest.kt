package com.example.impl

import app.cash.turbine.test
import com.example.api.data.PokemonResponse
import com.example.api.data.Pokemons
import com.example.database.repository.PokemonsDatabaseRepositoryImpl
import com.example.impl.domain.FeatureViewModel
import com.example.impl.domain.PokemonUpdateUseCase
import com.example.impl.repository.FeatureRepositoryImpl
import com.example.ui.state.UiState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.ConnectException

class FeatureViewModelTest {
    private val pokemonsRepository: FeatureRepositoryImpl = mockk()

    private val databaseRepository: PokemonsDatabaseRepositoryImpl = mockk()

    private val pokemonUpdateUseCase: PokemonUpdateUseCase = mockk()

    private val viewModel by lazy { FeatureViewModel(pokemonsRepository, pokemonUpdateUseCase, databaseRepository) }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `GIVEN pokemon request WHEN failed response THEN check if there is error message`() {
        runTest {
            coEvery { databaseRepository.initiateRequestPokemons() }.throws(ConnectException("No internet connection"))
            coEvery { databaseRepository.initiateClearPokemons() }.returns(Unit)
            every { pokemonUpdateUseCase.initiateUpdateTime() }.returns(Unit)
            every { pokemonUpdateUseCase.initiateCheckLastUpdateTime() }.returns(true)
            every { pokemonsRepository.pokemonsRequest() } returns flow {
                throw ConnectException("No internet connection")
            }

            viewModel.initiateRequestPokemons()

            viewModel.pokemonState.test {
                val error = awaitItem()
                assert(error is UiState.Error)
                Assert.assertEquals((error as UiState.Error).error.message, "No internet connection")
            }
        }
    }

    @Test
    fun `GIVEN one pokemon request WHEN successful response THEN check if there is one result`() {
        runBlocking {
            coEvery { databaseRepository.initiateInsertPokemon(any()) }.returns(Unit)
            coEvery { databaseRepository.initiateClearPokemons() }.returns(Unit)
            every { pokemonUpdateUseCase.initiateUpdateTime() }.returns(Unit)
            every { pokemonUpdateUseCase.initiateCheckLastUpdateTime() }.returns(true)
            every { pokemonsRepository.pokemonsRequest() } returns flowOf(pokemons)

            viewModel.initiateRequestPokemons()

            viewModel.pokemonState.test {
                val resultItem = awaitItem()
                assert(resultItem is UiState.Success)
                val pokemonsResult = (resultItem as UiState.Success).data
                assert(pokemonsResult.size == 3)
            }
        }
    }

    companion object {
        var pokemons = PokemonResponse(
            pokemons = arrayListOf(
                Pokemons("1", "Bulb", ""),
                Pokemons("2", "Curk", ""),
                Pokemons("3", "Derp", ""),
            ),
        )
    }
}
