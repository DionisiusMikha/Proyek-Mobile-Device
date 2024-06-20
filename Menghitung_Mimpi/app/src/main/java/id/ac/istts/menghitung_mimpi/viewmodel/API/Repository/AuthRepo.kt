package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.GetNameResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.AuthService
import id.ac.istts.menghitung_mimpi.viewmodel.Token

class AuthRepo (private val authService: AuthService) {
    suspend fun getName(token: String): Result<GetNameResponse<Any>> {
        println(token)
        return try {
            val response = authService.getName("Bearer ${token}")
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}