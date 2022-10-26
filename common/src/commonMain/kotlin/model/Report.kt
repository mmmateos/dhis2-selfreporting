package model

import kotlinx.serialization.Serializable

@Serializable
data class Report(
    val id: String,
    val diastolic: Int,
    val systolic: Int,
    val pulse: Int,
    val weight: Float,
    val date: String
)