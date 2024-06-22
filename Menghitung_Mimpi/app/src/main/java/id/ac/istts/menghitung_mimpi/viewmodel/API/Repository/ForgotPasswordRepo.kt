package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.CekOTPRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.CekOTPResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ForgotPasswordEmailRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ForgotPasswordEmailResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ResetPasswordRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ResetPasswordResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.ForgotPasswordService

class ForgotPasswordRepo (private val apiService: ForgotPasswordService) {
    suspend fun forgotPassword(email: String): Result<ForgotPasswordEmailResponse<Any>> {
        return try {
            val response = apiService.forgotPassword(ForgotPasswordEmailRequest(email))
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun cekOTP(email: String, otp: String): Result<CekOTPResponse<Any>> {
        return try {
            val response = apiService.cekOTP(CekOTPRequest(email, otp))
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun resetPassword(email: String, password: String, confirm_password: String): Result<ResetPasswordResponse<Any>> {
        return try {
            val response = apiService.resetPassword(ResetPasswordRequest(email, password, confirm_password))
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}