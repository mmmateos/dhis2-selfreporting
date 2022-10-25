package data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import model.Report

class ReportRepository {
    suspend fun sendReport(report: Report): Boolean {
        val client = HttpClient()
        val response: HttpResponse = client.get("https://ktor.io/")
        println(response.status)
        client.close()
        return true
    }
}