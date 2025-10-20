package com.silkfinik.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.silkfinik.ui.theme.EchoLogTheme
import com.silkfinik.ui.theme.ThemedPreview

@Composable
fun EchoCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = content
    )
}

@ThemedPreview
@Composable
private fun EchoCardPreview() {
    EchoLogTheme {
        EchoCard(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Это контент внутри карточки 'эхо'",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}