package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import model.Report

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JorunalScreen(
    reports: List<Report>
) {
    LazyColumn(
        Modifier.fillMaxWidth()
            .background(Color(0xFFFBFE))
            .padding(16.dp)
    ) {
        items(reports) {
            ReportItem(it)
        }
    }
}

@Composable
fun ReportItem(report: Report) {
    Column(Modifier.fillMaxWidth()) {
        RowItem("Diastolic", report.diastolic.toString())
        RowItem("Systolic", report.systolic.toString())
        RowItem("Pulse", report.pulse.toString())
        RowItem("Weight", report.weight.toString())
    }
}

@Composable
fun RowItem(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row {
            Icon(Icons.Outlined.CheckCircle, null, tint = MaterialTheme.colorScheme.primary)
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Text(text = value, fontWeight = FontWeight.Bold, textAlign = TextAlign.End)
    }
}

enum class BarScreen {
    Journal,
    Home,
    Profile
}