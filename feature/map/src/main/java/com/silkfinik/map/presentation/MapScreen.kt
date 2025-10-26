package com.silkfinik.map.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.silkfinik.feature.map.R
import com.silkfinik.ui.theme.EchoLogTheme

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
    onFabClick: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    MapScreenContent(
        state = state,
        onFabClick = onFabClick
    )
}

@Composable
private fun MapScreenContent(
    state: MapScreenState,
    onFabClick: () -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.create_new_echo)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            // TODO: Заменить на реальную начальную позицию пользователя
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(52.3676, 4.9041), 10f)
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                state.markers.forEach { marker ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                marker.latitude,
                                marker.longitude
                            )
                        ),
                        title = marker.id
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun MapScreenPreview() {
    EchoLogTheme {
        MapScreenContent(
            state = MapScreenState(
                isLoading = true,
                markers = listOf()
            ),
            onFabClick = {}
        )
    }
}