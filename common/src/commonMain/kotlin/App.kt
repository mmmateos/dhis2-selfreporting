import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import di.Injector
import model.Report

@Composable
fun App() {

    val viewModel = Injector.provideViewModel()

    MaterialTheme {
        var text by remember { mutableStateOf("Hello, World!") }

        Button(onClick = {
            text = "Hello, ${getPlatformName()}. Status: ${viewModel.sendReportStatus.value.status.name}"
            viewModel.sendReport(Report(1, 1, 1, 1F))
        }) {
            Text(text)
        }
    }
}

expect fun getPlatformName(): String