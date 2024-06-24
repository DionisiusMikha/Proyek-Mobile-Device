package id.ac.istts.menghitung_mimpi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ApiException
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.AuthRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthVM(private val authRepo: AuthRepo): ViewModel() {
    suspend fun getName(token: String, onSuccess: (String, Int, Int, String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepo.getName(token)
            result.onSuccess { response ->
                onSuccess(response.name, response.saldo, response.tabungan, response.email)
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