
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import di.Injector
import model.Status
import ui.Login
import ui.ReportScreen
import ui.ReportingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {

    val viewModel = Injector.provideViewModel()
    val screen = viewModel.screen

    MaterialTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = screen.value.name,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                )
            }
        ) {
            AppScreen(screen.value, viewModel)
        }
    }
}

@Composable
fun AppScreen(screen: Screen, viewModel: ReportingViewModel) {
    when (screen) {
        Screen.Login -> Login {
            viewModel.navigateToScreen(Screen.Report)
        }
        Screen.List -> Text("List")
        Screen.Report -> ReportScreen {
            viewModel.sendReport(it)

        }
    }
}

enum class Screen {
    Login,
    List,
    Report
}

expect fun getPlatformName(): String