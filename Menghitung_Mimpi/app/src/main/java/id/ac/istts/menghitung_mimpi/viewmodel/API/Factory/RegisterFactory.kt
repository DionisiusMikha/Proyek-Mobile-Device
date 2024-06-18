package id.ac.istts.menghitung_mimpi.viewmodel.API.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.RegisterRepo
import id.ac.istts.menghitung_mimpi.viewmodel.LoginVM
import id.ac.istts.menghitung_mimpi.viewmodel.RegisterVM

class RegisterFactory(private val registerRepo: RegisterRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterVM(registerRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}