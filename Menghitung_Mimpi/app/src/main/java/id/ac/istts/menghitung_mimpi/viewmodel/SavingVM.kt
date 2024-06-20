package id.ac.istts.menghitung_mimpi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ApiException
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.SavingRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavingVM(private val savingRepo: SavingRepo): ViewModel() {
    suspend fun saveInvest(token: String, target: Int, waktu: Int, uang_sekarang: Int, invest: Int, presentase: Int, final: Int, type: Int, status: Boolean, onSuccess: (String) -> Unit, onError: (String) -> Unit ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = savingRepo.saveInvest(token, target, waktu, uang_sekarang, invest, presentase, final, type, status)
            result.onSuccess { response ->
                onSuccess("Berhasil menyimpan!!")
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