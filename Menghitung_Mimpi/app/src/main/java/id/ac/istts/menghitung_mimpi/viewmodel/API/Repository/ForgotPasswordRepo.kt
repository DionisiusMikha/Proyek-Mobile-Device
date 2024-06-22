package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ForgotPasswordEmailRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.ForgotPasswordEmailResponse
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
}