package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScreen(viewModel: ReportingViewModel) {
    val reports = viewModel.reports.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(viewModel.barScreen.value) {
                viewModel.navigateToBarScreen(it)
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Icon(Icons.Default.Add, null)
                    Text("New report")
                },
                onClick = { viewModel.navigateToScreen(Screen.Report) }
            )
        }
    ) {
        when(viewModel.barScreen.value) {
            BarScreen.Journal -> JorunalScreen(reports.value)
            BarScreen.Home -> Text("Home")
            BarScreen.Profile -> Text("Profile")
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
                        imageVector = Icons.Default.List,
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