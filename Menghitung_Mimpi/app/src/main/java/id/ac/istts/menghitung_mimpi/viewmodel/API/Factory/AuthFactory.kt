package id.ac.istts.menghitung_mimpi.viewmodel.API.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.AuthRepo
import id.ac.istts.menghitung_mimpi.viewmodel.AuthVM

class AuthFactory(private val authRepo: AuthRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthVM(authRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}