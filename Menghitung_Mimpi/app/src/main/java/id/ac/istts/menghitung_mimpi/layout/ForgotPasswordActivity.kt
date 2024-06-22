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
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.LoginFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ForgotPasswordRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.LoginRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.ForgotPasswordVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var btnNextStep: Button
    lateinit var etEmailForgotPassword: EditText

    private val vm: ForgotPasswordVM by viewModels {
        ForgotPasswordFactory(ForgotPasswordRepo(RetrofitInstance.apiPassword))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnNextStep = findViewById(R.id.btnNextStep)
        etEmailForgotPassword = findViewById(R.id.etEmailForgotPassword)

        btnNextStep.setOnClickListener {
            val email: String = etEmailForgotPassword.text.toString()
            coroutine.launch {
                vm.forgotPassword(email, onSuccess = { message ->
                    runOnUiThread{
                        Toast.makeText(this@ForgotPasswordActivity, message, Toast.LENGTH_LONG).show()
                        val intent = Intent(this@ForgotPasswordActivity, SecurityPinActivity::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                        finish()
                    }
                }, onError = { error ->
                    runOnUiThread {
                        Toast.makeText(this@ForgotPasswordActivity, error, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}