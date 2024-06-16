package id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse<T>(
    @Json(name = "message") val message: String,
    @Json(name = "token") val token: String
)

@JsonClass(generateAdapter = true)
data class ErrorResponse<T>(
    @Json(name = "message") val message: String
)
