package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.UsersDataclass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UsersDataService {
    @GET("users/get-user-data")
    fun getUserData(@Header("Authorization") token: String): Call<UsersDataclass>
}
