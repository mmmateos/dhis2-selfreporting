package model

class SendReportStatus(
    val status: Status = Status.READY
)

enum class Status {
    READY,
    SUCCESS,
    FAIL
}