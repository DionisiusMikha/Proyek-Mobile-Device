package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.LoginRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.LoginResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.RegisterRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.RegisterResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("users/register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse<Any>
}