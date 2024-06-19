package id.ac.istts.menghitung_mimpi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ApiException
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.RegisterRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterVM(private val registerRepo: RegisterRepo): ViewModel() {
    suspend fun register(fullName: String, dob: String, phoneNumber: String, email: String, password: String, confirmPassword: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = registerRepo.register(fullName, dob, phoneNumber, email, password, confirmPassword)
            result.onSuccess { response ->
                onSuccess("Berhasil Register!!")
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