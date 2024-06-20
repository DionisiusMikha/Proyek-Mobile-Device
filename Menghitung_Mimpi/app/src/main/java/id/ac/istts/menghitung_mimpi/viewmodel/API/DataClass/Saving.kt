package id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SavingInvestRequest(
    @Json(name = "target") val target: Int,
    @Json(name = "waktu") val waktu: Int,
    @Json(name = "uang_sekarang") val uang_sekarang: Int,
    @Json(name = "invest") val invest: Int,
    @Json(name = "presentase") val presentase: Int,
    @Json(name = "final") val final: Int,
    @Json(name = "type") val type: Int,
    @Json(name = "status") val status: Boolean,
)

@JsonClass(generateAdapter = true)
data class SavingInvestResponse<T>(
    @Json(name = "message") val message: String
)

@JsonClass(generateAdapter = true)
data class SavingDanaDaruratRequest(
    @Json(name = "dana_darurat") val dana_darurat: Int,
    @Json(name = "dana_sekarang") val dana_sekarang: Int,
    @Json(name = "lama") val lama: Int,
    @Json(name = "invest") val invest: Int,
    @Json(name = "presentase") val presentase: Int,
    @Json(name = "total") val total: Int,
    @Json(name = "status") val status: Boolean,
)

@JsonClass(generateAdapter = true)
data class SavingDanaDaruratResponse<T>(
    @Json(name = "message") val message: String
)

@JsonClass(generateAdapter = true)
data class SavingNikahRequest(
    @Json(name = "biaya_final") val biaya_final: Int,
    @Json(name = "uang_sekarang") val uang_sekarang: Int,
    @Json(name = "invest") val invest: Int,
    @Json(name = "presentase") val presentase: Int,
    @Json(name = "waktu") val waktu: Int,
    @Json(name = "total_final") val total_final: Int,
    @Json(name = "status") val status: Boolean,
)

@JsonClass(generateAdapter = true)
data class SavingNikahResponse<T>(
    @Json(name = "message") val message: String
)
