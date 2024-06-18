package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import id.ac.istts.menghitung_mimpi.R

class LoginRegisterActivity : AppCompatActivity() {
    lateinit var btnSignUp: Button
    lateinit var btnLogin: Button
    lateinit var txtForgotPassword: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        btnLogin = findViewById(R.id.btnLoginLoginRegisterPage)
        btnSignUp = findViewById(R.id.btnSignUpLoginRegisterPage)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)

        btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        txtForgotPassword.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}