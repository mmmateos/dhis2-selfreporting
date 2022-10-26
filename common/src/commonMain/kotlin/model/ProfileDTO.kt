package model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileDTO(
    val status: String,
    val info: Profile
)