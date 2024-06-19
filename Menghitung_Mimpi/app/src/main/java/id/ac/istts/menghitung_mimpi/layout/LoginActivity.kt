package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.LoginFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.LoginRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.LoginVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var etEmailLoginPage: EditText
    lateinit var etPasswordLoginPage: EditText
    lateinit var tvToRegisterPage: TextView
    lateinit var btnLogIn: Button

    private val vm:LoginVM by viewModels {
        LoginFactory(LoginRepo(RetrofitInstance.apiLogin))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmailLoginPage = findViewById(R.id.etEmailLoginPage)
        etPasswordLoginPage = findViewById(R.id.etPasswordLoginPage)
        tvToRegisterPage = findViewById(R.id.tvToRegisterPage)
        btnLogIn = findViewById(R.id.btnLogin)

        tvToRegisterPage.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogIn.setOnClickListener {
            var email: String = etEmailLoginPage.text.toString()
            var password: String = etPasswordLoginPage.text.toString()
            coroutine.launch {
                vm.login(email, password, onSuccess = { message ->
                    runOnUiThread {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }, onError = { error ->
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, error, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}