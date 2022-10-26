package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Profile

@Composable
fun profileScreen(
    profile: Profile,
    onUpdateProfile: (Profile) -> Unit) {
    var firstName by remember { mutableStateOf(profile.firstName) }
    var lastName by remember { mutableStateOf(profile.lastName) }
    var dob by remember { mutableStateOf(profile.dob) }
    var phoneNumber by remember { mutableStateOf(profile.phone) }
    var email by remember { mutableStateOf(profile.email) }
    Column(Modifier.fillMaxWidth()) {
        profileField("First name", firstName) { firstName = it }
        profileField("Last name", lastName) { lastName = it }
        profileField("Date of birth", dob) { dob = it }
        profileField("Phone number", phoneNumber) { phoneNumber = it }
        profileField("Email", email) { email = it }
        Button(
            modifier = Modifier.align(Alignment.End).padding(16.dp),
            enabled = firstName.isNotEmpty() && lastName.isNotEmpty()
                    && dob.isNotEmpty(),
            onClick = {
                onUpdateProfile(
                    Profile(
                        firstName,
                        lastName,
                        dob,
                        phoneNumber,
                        email
                    )
                )
            }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Send, null, Modifier.size(18.dp), MaterialTheme.colorScheme.onPrimary)
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "Update",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun profileField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    )
}