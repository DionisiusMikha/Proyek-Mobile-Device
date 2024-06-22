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