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
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.RegisterFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.LoginRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.RegisterRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.LoginVM
import id.ac.istts.menghitung_mimpi.viewmodel.RegisterVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    lateinit var tvToLoginPage: TextView
    lateinit var etFullnameRegisterPage: EditText
    lateinit var etEmailRegisterPage: EditText
    lateinit var etMobileNumberRegisterPage: EditText
    lateinit var etDobRegisterPage: EditText
    lateinit var etPasswordRegisterPage: EditText
    lateinit var etConfirmPasswordRegisterPage: EditText
    lateinit var btnSignUpRegisterPage: Button

    private val vm: RegisterVM by viewModels {
        RegisterFactory(RegisterRepo(RetrofitInstance.apiRegister))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tvToLoginPage = findViewById(R.id.tvToLoginPage)
        etFullnameRegisterPage = findViewById(R.id.etFullnameRegisterPage)
        etEmailRegisterPage = findViewById(R.id.etEmailRegisterPage)
        etMobileNumberRegisterPage = findViewById(R.id.etMobileNumberRegisterPage)
        etDobRegisterPage = findViewById(R.id.etDobRegisterPage)
        etPasswordRegisterPage = findViewById(R.id.etPasswordRegisterPage)
        etConfirmPasswordRegisterPage = findViewById(R.id.etConfirmPasswordRegisterPage)
        btnSignUpRegisterPage = findViewById(R.id.btnSignUpRegisterPage)

        btnSignUpRegisterPage.setOnClickListener{
            var full_name: String = etFullnameRegisterPage.text.toString()
            var email: String = etEmailRegisterPage.text.toString()
            var phone_number: String = etMobileNumberRegisterPage.text.toString()
            var dob: String = etDobRegisterPage.text.toString()
            var password: String = etPasswordRegisterPage.text.toString()
            var confirm_password: String = etConfirmPasswordRegisterPage.text.toString()
            if(full_name.isEmpty() || email.isEmpty() || phone_number.isEmpty() || dob.isEmpty() || password.isEmpty() || confirm_password.isEmpty())
                Toast.makeText(this@RegisterActivity, "Semua field wajib diisi", Toast.LENGTH_LONG).show()
            coroutine.launch {
                vm.register(full_name, dob, phone_number, email, password, confirm_password, onSuccess = { message ->
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, "Berhasil Regsiter!!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }, onError = { error ->
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, error, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        tvToLoginPage.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}