package ui

import Screen
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import data.ReportRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.Profile
import model.Report
import model.SendReportStatus
import model.Status

class ReportingViewModel(
    private val repository: ReportRepository
) {

    private val _sendReportStatus = MutableStateFlow(SendReportStatus())
    val sendReportStatus: StateFlow<SendReportStatus> = _sendReportStatus

    private var _screen = mutableStateOf(Screen.Login)
    val screen: MutableState<Screen>
        get() = _screen

    private var _barScreen = mutableStateOf(BarScreen.Journal)
    val barScreen: MutableState<BarScreen>
        get() = _barScreen

    private val _reports = MutableStateFlow(listOf<Report>())
    val reports: StateFlow<List<Report>>
        get() = _reports

    private val _profile = MutableStateFlow(Profile())
    val profile: StateFlow<Profile>
        get() = _profile

    fun sendReport(report: Report) {
        CoroutineScope(Dispatchers.Main).launch {
            _sendReportStatus.value = SendReportStatus(
                when (repository.sendReport(report)) {
                    true -> {
                        addReport(report)
                        navigateToScreen(Screen.Main)
                        Status.SUCCESS
                    }

                    false -> Status.FAIL
                }
            )
        }
    }

    fun updateProfile(profile: Profile) {
        // TODO Update profile
    }

    fun navigateToScreen(screen: Screen) {
        _screen.value = screen
    }

    fun navigateToBarScreen(screen: BarScreen) {
        _barScreen.value = screen
    }


    private fun addReport(report: Report) {
        _reports.value = _reports.value.plus(report)
    }

    fun sendAuthentication(code: String) {
        CoroutineScope(Dispatchers.Main).launch {
            when (repository.sendAuthentication(code)) {
                true -> navigateToScreen(Screen.Main)
                false -> {} //TODO show toast
            }
        }
    }

    fun retrieveProfile() {
        CoroutineScope(Dispatchers.Main).launch {
            _profile.value = repository.receiveProfile()
        }
    }
}