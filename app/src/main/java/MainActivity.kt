package com.silkfinik.echolog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.silkfinik.echolog.navigation.AppNavigation
import com.silkfinik.ui.theme.EchoLogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EchoLogTheme {
                AppNavigation(modifier = Modifier.fillMaxSize())
            }
        }
    }
}