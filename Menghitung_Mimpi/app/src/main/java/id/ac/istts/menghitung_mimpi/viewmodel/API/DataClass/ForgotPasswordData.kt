package id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForgotPasswordEmailRequest(
    @Json(name = "email") val email: String
)

@JsonClass(generateAdapter = true)
data class ForgotPasswordEmailResponse<T>(
    @Json(name = "message") val message: String
)

@JsonClass(generateAdapter = true)
data class CekOTPRequest(
    @Json(name = "email") val email: String,
    @Json(name = "otp") val otp: String
)

@JsonClass(generateAdapter = true)
data class CekOTPResponse<T>(
    @Json(name = "message") val message: String
)

@JsonClass(generateAdapter = true)
data class ResetPasswordRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "confirm_password") val confirm_password: String
)

@JsonClass(generateAdapter = true)
data class ResetPasswordResponse<T>(
    @Json(name = "message") val message: String
)