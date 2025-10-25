package com.silkfinik.map.presentation

import com.silkfinik.map.presentation.model.MapMarkerUiModel

data class MapScreenState(
    val isLoading: Boolean = true,
    val markers: List<MapMarkerUiModel> = emptyList()
)
