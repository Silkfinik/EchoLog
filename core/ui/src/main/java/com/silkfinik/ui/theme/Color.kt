package com.silkfinik.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val DayBackground = Color(0xFFF7F8FA)
val DaySurface = Color(0xFFFFFFFF)
val DayOnBackground = Color(0xFF1C1C1E)
val DayOnSurface = Color(0xFF1C1C1E)
val DayPrimary = Color(0xFF3A5A9B)
val DayOnPrimary = Color(0xFFFFFFFF)

val NightBackground = Color(0xFF1A1D28)
val NightSurface = Color(0xFF24283B)
val NightOnBackground = Color(0xFFDCE1E9)
val NightOnSurface = Color(0xFFDCE1E9)
val NightPrimary = Color(0xFFA8DADC)
val NightOnPrimary = Color(0xFF1A1D28)

internal val DarkColorScheme = darkColorScheme(
    primary = NightPrimary,
    onPrimary = NightOnPrimary,
    background = NightBackground,
    surface = NightSurface,
    onBackground = NightOnBackground,
    onSurface = NightOnSurface
)

internal val LightColorScheme = lightColorScheme(
    primary = DayPrimary,
    onPrimary = DayOnPrimary,
    background = DayBackground,
    surface = DaySurface,
    onBackground = DayOnBackground,
    onSurface = DayOnSurface
)