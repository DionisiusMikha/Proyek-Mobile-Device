package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import id.ac.istts.menghitung_mimpi.R

class LoginRegisterActivity : AppCompatActivity() {
    lateinit var btnSignUpLoginRegisterPage: Button
    lateinit var btnLoginLoginRegisterPage: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        btnLoginLoginRegisterPage = findViewById(R.id.btnLoginLoginRegisterPage)
        btnSignUpLoginRegisterPage = findViewById(R.id.btnSignUpLoginRegisterPage)

        btnSignUpLoginRegisterPage.setOnClickListener {
            val intent = Intent(this, IntroSliderActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLoginLoginRegisterPage.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}