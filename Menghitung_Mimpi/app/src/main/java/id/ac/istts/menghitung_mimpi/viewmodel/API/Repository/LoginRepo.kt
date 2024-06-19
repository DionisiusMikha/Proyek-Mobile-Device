package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.LoginRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.LoginResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.LoginService

class LoginRepo (private val apiService: LoginService) {
    suspend fun login(email: String, password: String): Result<LoginResponse<Any>> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            println(response)
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}