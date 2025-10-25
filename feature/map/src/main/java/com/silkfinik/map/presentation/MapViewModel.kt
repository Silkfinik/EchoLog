package com.silkfinik.map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silkfinik.domain.usecase.GetEchoesForMapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getEchoesForMapUseCase: GetEchoesForMapUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MapScreenState())

    val state: StateFlow<MapScreenState> = _state.asStateFlow()

    init {
        observeEchoes()
    }

    private fun observeEchoes() {
        getEchoesForMapUseCase()
            .onStart {
                _state.value = _state.value.copy(isLoading = true)
            }
            .map{ echoList ->
                echoList.map { echo ->
                    echo.toUiModel()
                }
            }
            .onEach { markerList ->
                _state.value = MapScreenState(
                    isLoading = false,
                    markers = markerList
                )
            }
            .catch {
                //TODO: add error handling
                _state.value = _state.value.copy(isLoading = false)
            }
            .launchIn(viewModelScope)
    }
}