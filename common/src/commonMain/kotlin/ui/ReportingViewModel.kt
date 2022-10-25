package ui

import data.ReportRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.Report
import model.SendReportStatus
import model.Status

class ReportingViewModel(
    private val repository: ReportRepository
) {

    private val _sendReportStatus = MutableStateFlow(SendReportStatus())
    val sendReportStatus: StateFlow<SendReportStatus> = _sendReportStatus


    fun sendReport(report: Report) {
        CoroutineScope(Dispatchers.Main).launch {
            _sendReportStatus.value = SendReportStatus(
                when (repository.sendReport(report)) {
                    true -> Status.SUCCESS
                    false -> Status.FAIL
                }
            )
        }
    }
}