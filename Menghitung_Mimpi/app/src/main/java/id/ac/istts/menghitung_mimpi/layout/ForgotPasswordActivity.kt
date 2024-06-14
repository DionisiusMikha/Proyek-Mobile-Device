package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import id.ac.istts.menghitung_mimpi.R

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var btnNextStep: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnNextStep = findViewById(R.id.btnNextStep)

        btnNextStep.setOnClickListener {
            val intent = Intent(this, SecurityPinActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}