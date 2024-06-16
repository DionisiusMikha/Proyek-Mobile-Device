package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.LoginRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse<Any>
}