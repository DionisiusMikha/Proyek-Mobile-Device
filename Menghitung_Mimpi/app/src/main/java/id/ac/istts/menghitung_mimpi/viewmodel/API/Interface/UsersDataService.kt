package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.UsersDataclass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UsersDataService {
    @GET("users/get-user-data")
    fun getUserData(@Header("Authorization") token: String): Call<UsersDataclass>

    @POST("pocket/createpocket")
    fun create(
        @Header("Authorization") token: String,
        @Body request: UpdatePocketRequest
    ): Call<Void>
}
