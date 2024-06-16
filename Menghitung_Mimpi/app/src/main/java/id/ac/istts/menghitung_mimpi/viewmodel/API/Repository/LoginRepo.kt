package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.LoginRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.LoginResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.LoginService

class LoginRepo (private val apiService: LoginService) {
    suspend fun login(email: String, password: String): LoginResponse<Any> {
        val response = apiService.login(LoginRequest(email, password))
        return response
    }
}