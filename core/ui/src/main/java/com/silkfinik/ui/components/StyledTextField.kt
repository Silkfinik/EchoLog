package com.silkfinik.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.silkfinik.ui.theme.EchoLogTheme
import com.silkfinik.ui.theme.ThemedPreview

@Composable
fun StyledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "cursor-blink")
    val cursorAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cursor-alpha"
    )

    val cursorColor = MaterialTheme.colorScheme.primary

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        cursorBrush = SolidColor(cursorColor.copy(alpha = cursorAlpha)),
        decorationBox = { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    placeholder()
                }
                innerTextField()
            }
        }
    )
}

@ThemedPreview
@Composable
private fun StyledTextFieldPreview() {
    var text by remember { mutableStateOf("Какой-то введенный текст") }
    EchoLogTheme(useDarkTheme = true) {
        StyledTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(16.dp),
            placeholder = { Text("Это плейсхолдер...") }
        )
    }
}