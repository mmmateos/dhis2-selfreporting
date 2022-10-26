package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Profile
import model.Report

@Composable
fun profileScreen(onUpdateProfile: (Profile) -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Column(Modifier.fillMaxWidth()) {
        profileField("First name", firstName) { firstName = it }
        profileField("Last name", lastName) { lastName = it }
        profileField("Date of birth", dob) { dob = it }
        profileField("Phone number", phoneNumber) { phoneNumber = it }
        profileField("Email", email) { email = it }
        Button(
            modifier = Modifier.align(Alignment.End).padding(16.dp),
            enabled = firstName.isNotEmpty() && lastName.isNotEmpty()
                    && dob.isNotEmpty() && phoneNumber.isNotEmpty(),
            onClick = {
                onUpdateProfile(
                    Profile(
                        firstName.toInt(),
                        lastName.toInt(),
                        dob,
                        phoneNumber.toInt(),
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