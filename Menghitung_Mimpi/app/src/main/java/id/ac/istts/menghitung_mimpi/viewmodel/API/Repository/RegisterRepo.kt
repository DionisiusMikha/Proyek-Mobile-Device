package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.RegisterRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.RegisterResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.RegisterService

class RegisterRepo (private val apiService: RegisterService) {
    suspend fun register(fullName: String, dob: String, phoneNumber: String, email: String, password: String, confirmPassword: String): Result<RegisterResponse<Any>> {
        return try {
            val response = apiService.register(RegisterRequest(fullName, dob, phoneNumber, email, password, confirmPassword))
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}