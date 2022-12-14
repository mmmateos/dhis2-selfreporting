package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import model.Report

@Composable
fun ReportScreen(onSendReport: (Report) -> Unit) {
    var diastolic by remember { mutableStateOf("") }
    var systolic by remember { mutableStateOf("") }
    var pulse by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    Column(Modifier.fillMaxWidth()) {
        ReportField("Diastolic (mmHg)", diastolic) { diastolic = it }
        ReportField("Systolic (mmHg)", systolic) { systolic = it }
        ReportField("Pulse (bpm)", pulse) { pulse = it }
        ReportField("Wight (Kg)", weight) { weight = it }
        Button(
            modifier = Modifier.align(Alignment.End).padding(16.dp),
            enabled = diastolic.isNotEmpty() && systolic.isNotEmpty()
                    && pulse.isNotEmpty() && weight.isNotEmpty(),
            onClick = {
                onSendReport(
                    Report(
                        "dwWgzxGz0S4",
                        diastolic.toInt(),
                        systolic.toInt(),
                        pulse.toInt(),
                        weight.toFloat(),
                        "2022-10-26"
                    )
                )
            }) {
            Text("Send")
        }
    }
}

@Composable
fun ReportField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    )
}