package com.silkfinik.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.silkfinik.ui.R
import com.silkfinik.ui.theme.EchoLogTheme
import com.silkfinik.ui.theme.ThemedPreview

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(
            1.dp,
            if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun HoldToSealButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = stringResource(R.string.hold_to_seal),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@ThemedPreview
@Composable
private fun ButtonsPreview() {
    EchoLogTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            PrimaryButton(text = "Primary Action", onClick = {})
            Spacer(Modifier.height(8.dp))
            PrimaryButton(text = "Primary Disabled", onClick = {}, enabled = false)
            Spacer(Modifier.height(8.dp))
            SecondaryButton(text = "Secondary Action", onClick = {})
            Spacer(Modifier.height(8.dp))
            SecondaryButton(text = "Secondary Disabled", onClick = {}, enabled = false)
        }
    }
}


@ThemedPreview
@Composable
private fun HoldToSealButtonPreview_Light() {
    EchoLogTheme(useDarkTheme = false) {
        Column(modifier = Modifier.padding(16.dp)) {
            HoldToSealButton(onClick = {})
            Spacer(Modifier.height(8.dp))
            HoldToSealButton(onClick = {}, enabled = false)
        }
    }
}