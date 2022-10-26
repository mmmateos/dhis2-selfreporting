package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun mainHeader(title: String) {
    Column(modifier = Modifier.padding(top = 80.dp, start = 20.dp, end = 16.dp).fillMaxWidth())  {
        Icon(Icons.Filled.AccountCircle, null, Modifier.align(Alignment.End)
            .size(60.dp), tint = MaterialTheme.colorScheme.primary)
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}