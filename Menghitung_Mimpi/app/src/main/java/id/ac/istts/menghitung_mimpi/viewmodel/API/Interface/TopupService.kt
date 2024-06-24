package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.PocketItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TopupService {
    @GET("pockets")
    fun getTopupItems(@Header("Authorization") token: String): Call<List<PocketItem>>
}
