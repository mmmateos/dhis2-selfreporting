package model

import kotlinx.serialization.Serializable

@Serializable
data class VitalsDTO(
    val status: String,
    val vitals: List<Report>
)