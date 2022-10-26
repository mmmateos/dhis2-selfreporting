package data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import model.Report

class ReportRepository {
    suspend fun sendReport(report: Report): Boolean {
        val client = HttpClient()
        val body = "{\n" +
                "    \"id\": \"${report.id}\",\n" +
                "    \"systolic\": \"${report.systolic}\",\n" +
                "    \"diastolic\": \"${report.diastolic}\",\n" +
                "    \"pulse\": \"${report.pulse}\",\n" +
                "    \"weight\": \"${report.weight}\"\n" +
                "}"
        println(body)
        val response: HttpResponse = client.post("http://172.104.146.122:8080/api/self-reporting/vital-signs") {
            contentType(ContentType.Application.Json)

            setBody(body)
        }
        println("Response Status $response.status")
        val result = response.status == HttpStatusCode.OK
        client.close()
        return result
    }
}