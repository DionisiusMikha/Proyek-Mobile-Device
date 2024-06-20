package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import id.ac.istts.menghitung_mimpi.R

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var btnNextStep: Button
    lateinit var etEmailForgotPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnNextStep = findViewById(R.id.btnNextStep)
        etEmailForgotPassword = findViewById(R.id.etEmailForgotPassword)

        btnNextStep.setOnClickListener {
            val intent = Intent(this, SecurityPinActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
