package id.ac.istts.menghitung_mimpi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ApiException
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ForgotPasswordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForgotPasswordVM(private val forgotPasswordRepo: ForgotPasswordRepo): ViewModel() {
    suspend fun forgotPassword(email: String, onSuccess: (String) -> Unit, onError: (String) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val result = forgotPasswordRepo.forgotPassword(email)
            result.onSuccess { response ->
                onSuccess(response.message)
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