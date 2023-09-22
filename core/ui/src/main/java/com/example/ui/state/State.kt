package com.example.ui.state

/**
 * UiState class for request processing, Loading state for request that is in process
 * Error in case of error occured, Success for successful results
 */
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()

    class Error(val error: Throwable) : UiState<Nothing>()

    class Success<T>(val data: T) : UiState<T>()
}
