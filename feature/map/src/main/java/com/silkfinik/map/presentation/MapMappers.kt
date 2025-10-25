package com.silkfinik.map.presentation

import com.silkfinik.domain.model.Echo
import com.silkfinik.map.presentation.model.MapMarkerUiModel

internal fun Echo.toUiModel(): MapMarkerUiModel = MapMarkerUiModel(
    id = this.id,
    latitude = this.latitude,
    longitude = this.longitude
)