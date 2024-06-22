package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.ForgotPasswordFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ForgotPasswordRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.ForgotPasswordVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPasswordActivity : AppCompatActivity() {
    lateinit var etNewPassword: EditText
    lateinit var etConfirmNewPassword: EditText
    lateinit var btnCangePassword: Button

    private val vm: ForgotPasswordVM by viewModels {
        ForgotPasswordFactory(ForgotPasswordRepo(RetrofitInstance.apiPassword))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        etNewPassword = findViewById(R.id.etNewPassword)
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword)
        btnCangePassword = findViewById(R.id.btnCangePassword)

        val email: String? = intent.getStringExtra("email")

        btnCangePassword.setOnClickListener{
            val password: String = etNewPassword.text.toString()
            val confirm_password: String = etConfirmNewPassword.text.toString()

           coroutine.launch {
               vm.resetPassword(email!!, password, confirm_password, onSuccess = { message ->
                   runOnUiThread {
                       Toast.makeText(this@NewPasswordActivity, message, Toast.LENGTH_LONG).show()
                       val intent = Intent(this@NewPasswordActivity, LoginActivity::class.java)
                       startActivity(intent)
                       finish()
                   }
               }, onError = { error ->
                   runOnUiThread {
                       Toast.makeText(this@NewPasswordActivity, error, Toast.LENGTH_LONG).show()
                   }
               })
           }
        }
    }
}