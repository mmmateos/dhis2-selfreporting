package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import model.LoginData

@Composable
fun logIn(onLogin: (LoginData) -> Unit) {

    var identifier by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(Modifier.fillMaxWidth()) {
        loginField("Identifier", identifier, Icons.Outlined.AccountCircle) { identifier = it }
        loginField("Phone number", phoneNumber, Icons.Outlined.Phone) { phoneNumber = it }
        Button(
            modifier = Modifier.align(Alignment.End).padding(16.dp),
            enabled = identifier.isNotEmpty() && phoneNumber.isNotEmpty(),
            onClick = {
                onLogin(
                    LoginData(
                        identifier,
                        phoneNumber.toInt()
                    )
                )
            }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.ArrowForward, null, Modifier.size(18.dp), MaterialTheme.colorScheme.onPrimary)
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "Next",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun loginField(label: String, value: String, leadingIcon: ImageVector, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(leadingIcon, null) },
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    )
}