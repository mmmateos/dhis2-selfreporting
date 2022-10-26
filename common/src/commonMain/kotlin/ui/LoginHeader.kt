package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun logInHeader(title: String, subTitle: String, icon: ImageVector) {
    Column(modifier = Modifier.padding(top = 80.dp, start = 16.dp, end = 16.dp)) {
        Icon(icon, null, Modifier.size(60.dp), tint = MaterialTheme.colorScheme.primary)
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = subTitle,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}