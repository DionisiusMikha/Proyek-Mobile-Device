package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.PocketItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

data class UpdatePocketRequest(
    val nama_pocket: String,
    val saldo_pocket: Int
)

interface TopupService {
    @GET("pockets")
    fun getTopupItems(@Header("Authorization") token: String): Call<List<PocketItem>>

    @PUT("updatepocket/{id}")
    fun updatePocket(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Body request: UpdatePocketRequest
    ): Call<Void>
}
