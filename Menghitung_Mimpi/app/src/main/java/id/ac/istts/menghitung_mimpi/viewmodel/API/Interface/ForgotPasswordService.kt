package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ForgotPasswordEmailRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ForgotPasswordEmailResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgotPasswordService {
    @POST("users/forgot-password")
    suspend fun forgotPassword(@Body forgotPasswordEmailRequest: ForgotPasswordEmailRequest): ForgotPasswordEmailResponse<Any>
}