package id.ac.istts.menghitung_mimpi.viewmodel

import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ApiException
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.LoginRepo
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LoginVM(private val loginRepo: LoginRepo): ViewModel() {
//    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("http://10.0.2.2:3000/api/")
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .build()
//
//    private val apiService = retrofit.create(LoginService::class.java)

    suspend fun login(email: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginRepo.login(email, password)
            result.onSuccess { response ->
                withContext(Dispatchers.Main) {
                    Token.setToken(response.token)
                }
                onSuccess("Berhasil Login!!")
            }.onFailure { throwable ->
                if (throwable is ApiException) {
                    onError(throwable.message ?: "Unknown error")
                } else {
                    onError(throwable.message ?: "Unknown error")
                }
            }
        }
    }
}