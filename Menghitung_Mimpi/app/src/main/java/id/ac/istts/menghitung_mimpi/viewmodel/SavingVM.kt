package id.ac.istts.menghitung_mimpi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.DanaDarurat
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Invest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Nikah
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ApiException
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.SavingRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    suspend fun saveDanaDarurat(token: String, dana_darurat: Int, dana_sekarang: Int, lama: Int, invest: Int, presentase: Int, total: Int, status: Boolean, onSuccess: (String) -> Unit, onError: (String) -> Unit ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = savingRepo.saveDanaDarurat(token, dana_darurat, dana_sekarang, lama, invest, presentase, total, status)
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

    suspend fun saveNikah(token: String, biaya_final: Int, uang_sekarang: Int, invest: Int, presentase: Int, waktu: Int, total_final: Int, status: Boolean, onSuccess: (String) -> Unit, onError: (String) -> Unit ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = savingRepo.saveNikah(token, biaya_final, uang_sekarang, invest, presentase, waktu, total_final, status)
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

    suspend fun getInvest(token: String, onSuccess: (List<Invest>) -> Unit, onError: (String) -> Unit ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = savingRepo.getInvest(token)
            result.onSuccess { response ->
                println(response)
                onSuccess(response)
            }.onFailure { throwable ->
                if (throwable is ApiException) {
                    onError(throwable.message ?: "Unknown error")
                } else {
                    onError(throwable.message ?: "Unknown error")
                }
            }
        }
    }

    suspend fun getDanaDarurat(token: String, onSuccess: (List<DanaDarurat>) -> Unit, onError: (String) -> Unit ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = savingRepo.getDanaDarurat(token)
            result.onSuccess { response ->
                onSuccess(response)
            }.onFailure { throwable ->
                if (throwable is ApiException) {
                    onError(throwable.message ?: "Unknown error")
                } else {
                    onError(throwable.message ?: "Unknown error")
                }
            }
        }
    }

    suspend fun getNikah(token: String, onSuccess: (List<Nikah>) -> Unit, onError: (String) -> Unit ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = savingRepo.getNikah(token)
            result.onSuccess { response ->
                println(response)
                onSuccess(response)
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