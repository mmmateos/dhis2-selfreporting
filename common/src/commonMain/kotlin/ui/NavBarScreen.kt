package ui

import Screen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScreen(viewModel: ReportingViewModel) {
    val reports by viewModel.reports.collectAsState()
    val profile by viewModel.profile.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(viewModel.barScreen.value) {
                viewModel.navigateToBarScreen(it)
            }
        },
        floatingActionButton = {
            when (viewModel.barScreen.value) {
                BarScreen.Profile -> { }
                else -> {
                    ExtendedFloatingActionButton(
                        text = {
                            Icon(Icons.Default.Add, null)
                            Text("New report", Modifier.padding(start = 16.dp))
                        },
                        onClick = { viewModel.navigateToScreen(Screen.Report) }
                    )
                }
            }
        }
    ) {
        when (viewModel.barScreen.value) {
            BarScreen.Journal -> JorunalScreen(reports)
            BarScreen.Home -> Text("Home")
            BarScreen.Profile -> profileScreen(profile) {
                viewModel.updateProfile(it)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(currentRoute: BarScreen, onBarItemSelected: (BarScreen) -> Unit) {
    NavigationBar {
        BarScreen.values().toList().forEach {
            NavigationBarItem(
                selected = currentRoute.name == it.name,
                onClick = {
                    onBarItemSelected(it)
                },
                icon = {
                    Icon(
                        imageVector = getIcon(it),
                        contentDescription = it.name
                    )
                },
                label = {
                    Text(text = it.name, fontSize = 14.sp)
                }
            )
        }
    }
}

fun getIcon(barScreen: BarScreen): ImageVector {
    return when (barScreen) {
        BarScreen.Journal -> Icons.Default.List
        BarScreen.Home -> Icons.Outlined.Home
        BarScreen.Profile -> Icons.Outlined.AccountCircle
    }
}