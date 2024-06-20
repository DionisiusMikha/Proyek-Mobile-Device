package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.GetNameResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthService {
    @GET("users/get-name")
    suspend fun getName(
        @Header("authorization") authorization: String?
    ): GetNameResponse<Any>
}