package id.ac.istts.menghitung_mimpi.viewmodel.API.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ForgotPasswordRepo
import id.ac.istts.menghitung_mimpi.viewmodel.ForgotPasswordVM

class ForgotPasswordFactory(private val forgotPasswordRepo: ForgotPasswordRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForgotPasswordVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ForgotPasswordVM(forgotPasswordRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}