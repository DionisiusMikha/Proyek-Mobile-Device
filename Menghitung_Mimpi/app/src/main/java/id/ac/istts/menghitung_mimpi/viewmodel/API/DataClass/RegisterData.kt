package id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    @Json(name = "full_name") val full_name: String,
    @Json(name = "dob") val dob: String,
    @Json(name = "phone_number") val phone_number: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "confirm_password") val confirm_password: String
)

@JsonClass(generateAdapter = true)
data class RegisterResponse<T>(
    @Json(name = "message") val message: String
)