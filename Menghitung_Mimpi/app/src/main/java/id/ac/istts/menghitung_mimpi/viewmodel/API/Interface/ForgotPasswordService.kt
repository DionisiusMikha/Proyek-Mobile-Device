package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.CekOTPRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.CekOTPResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ForgotPasswordEmailRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ForgotPasswordEmailResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ResetPasswordRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ResetPasswordResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgotPasswordService {
    @POST("users/forgot-password")
    suspend fun forgotPassword(@Body forgotPasswordEmailRequest: ForgotPasswordEmailRequest): ForgotPasswordEmailResponse<Any>

    @POST("users/cek-otp")
    suspend fun cekOTP(@Body cekOTPRequest: CekOTPRequest): CekOTPResponse<Any>

    @POST("users/reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse<Any>
}