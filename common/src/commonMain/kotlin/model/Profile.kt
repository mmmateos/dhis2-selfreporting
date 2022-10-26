package model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = "",
    val phone: String = "",
    val email: String = ""
)