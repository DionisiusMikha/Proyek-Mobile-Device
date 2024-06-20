package id.ac.istts.menghitung_mimpi.viewmodel.API.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.SavingRepo
import id.ac.istts.menghitung_mimpi.viewmodel.SavingVM

class SavingFactory(private val savingRepo: SavingRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavingVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavingVM(savingRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}