import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import di.Injector
import kotlinx.coroutines.launch
import model.Status
import ui.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {

    val viewModel = Injector.provideViewModel()
    val screen = viewModel.screen
    val snackbarHostState = remember { SnackbarHostState() }

    MaterialTheme {
        Scaffold(
            topBar = {
                appHeader(screen.value)
            }
        ) {
            SnackbarHost(snackbarHostState)
            AppScreen(screen.value, viewModel)
        }
    }

    val scope = rememberCoroutineScope()
    scope.launch {
        viewModel.sendReportStatus.collect {
            when (it.status) {
                Status.SUCCESS -> snackbarHostState.showSnackbar(
                    message = "Report successfully sent"
                )

                Status.FAIL -> snackbarHostState.showSnackbar(
                    message = "Fail to send the report"
                )

                else -> {}
            }
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

        Screen.Main -> NavBarScreen(viewModel)
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
        Screen.Main -> mainHeader("Journal")
        Screen.Report -> mainHeader("Report")
        Screen.Profile -> mainHeader("Profile")
    }
}

enum class Screen {
    Login,
    Authentication,
    Main,
    Report,
    Profile
}

expect fun getPlatformName(): String