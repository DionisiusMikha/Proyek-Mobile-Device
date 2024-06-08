package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import id.ac.istts.menghitung_mimpi.R

class RegisterActivity : AppCompatActivity() {
    lateinit var tvToLoginPage: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tvToLoginPage = findViewById(R.id.tvToLoginPage)

        tvToLoginPage.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}