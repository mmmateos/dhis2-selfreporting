import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import di.Injector
import ui.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {

    val viewModel = Injector.provideViewModel()
    val screen = viewModel.screen

    MaterialTheme {
        Scaffold(
            topBar = {
                appHeader(screen.value)
            }
        ) {
            AppScreen(screen.value, viewModel)
        }
    }
}

@Composable
fun AppScreen(screen: Screen, viewModel: ReportingViewModel) {
    when (screen) {
        Screen.Login -> logIn {
            viewModel.navigateToScreen(Screen.Authentication)
        }

        Screen.Authentication -> authenticationScreen {
            viewModel.sendAuthentication(it)
        }

        Screen.List -> Text("List")
        Screen.Report -> ReportScreen {
            viewModel.sendReport(it)

        }
    }
}

@Composable
fun appHeader(screen: Screen) {
    when (screen) {
        Screen.Login -> logInHeader("Self reporting app", "Log in", Icons.Outlined.AccountCircle)
        Screen.Authentication -> logInHeader("Self reporting app", "Verification code", Icons.Outlined.Lock)
        Screen.List -> mainHeader("Journal")
        Screen.Report -> mainHeader("Report")
    }
}

enum class Screen {
    Login,
    Authentication,
    List,
    Report
}

expect fun getPlatformName(): String