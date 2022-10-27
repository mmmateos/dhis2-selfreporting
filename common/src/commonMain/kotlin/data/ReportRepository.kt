package data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import model.Profile
import model.ProfileDTO
import model.Report
import model.VitalsDTO

class ReportRepository {

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun sendReport(report: Report): Boolean {
        val body = "{\n" +
                "    \"id\": \"${report.id}\",\n" +
                "    \"systolic\": \"${report.systolic}\",\n" +
                "    \"diastolic\": \"${report.diastolic}\",\n" +
                "    \"pulse\": \"${report.pulse}\",\n" +
                "    \"weight\": \"${report.weight}\"\n" +
                "}"
        println(body)
        val response: HttpResponse =
            client.post("https://sr.winterop.cloud/api/self-reporting/vital-signs") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        println("Response Status $response.status")
        val result = response.status == HttpStatusCode.OK
        client.close()
        return result
    }

    suspend fun sendAuthentication(code: String): Boolean {
        //receiveReports()
        return true
    }

    suspend fun receiveReports(): List<Report> {
        val response: HttpResponse =
            client.get("https://sr.winterop.cloud/api/self-reporting/vital-signs/dwWgzxGz0S4")
        val vitals: VitalsDTO = response.body()
        println(vitals.vitals)
        return vitals.vitals
    }

    suspend fun receiveProfile(): Profile {
        val response: HttpResponse =
            client.get("https://sr.winterop.cloud/api/self-reporting/info/dwWgzxGz0S4")
        val profileDTO: ProfileDTO = response.body()
        println(profileDTO)
        return profileDTO.info
    }
}