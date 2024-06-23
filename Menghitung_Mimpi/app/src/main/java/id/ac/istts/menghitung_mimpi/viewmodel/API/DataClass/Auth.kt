package id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetNameResponse<T>(
    @Json(name = "name") val name: String,
    @Json(name = "saldo") val saldo: Int,
    @Json(name = "tabungan") val tabungan: Int
)
