package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import id.ac.istts.menghitung_mimpi.R

class LoginActivity : AppCompatActivity() {
    lateinit var tvToRegisterPage: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvToRegisterPage = findViewById(R.id.tvToRegisterPage)

        tvToRegisterPage.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}