package id.ac.istts.menghitung_mimpi.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import id.ac.istts.menghitung_mimpi.R

class SecurityPinActivity : AppCompatActivity() {
    lateinit var etPin1: EditText
    lateinit var etPin2: EditText
    lateinit var etPin3: EditText
    lateinit var etPin4: EditText
    lateinit var etPin5: EditText
    lateinit var etPin6: EditText
    lateinit var btnAccept: Button
    lateinit var btnSendAgain: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_pin)

        etPin1 = findViewById(R.id.etPin1)
        etPin2 = findViewById(R.id.etPin2)
        etPin3 = findViewById(R.id.etPin3)
        etPin4 = findViewById(R.id.etPin4)
        etPin5 = findViewById(R.id.etPin5)
        etPin6 = findViewById(R.id.etPin6)
        btnAccept = findViewById(R.id.btnAccept)
        btnSendAgain = findViewById(R.id.btnSendAgain)

        setupEditTexts()

        btnAccept.setOnClickListener{

        }

        btnSendAgain.setOnClickListener{

        }
    }

    private fun setupEditTexts() {
        etPin1.addTextChangedListener(PinTextWatcher(etPin1, etPin2))
        etPin2.addTextChangedListener(PinTextWatcher(etPin2, etPin3))
        etPin3.addTextChangedListener(PinTextWatcher(etPin3, etPin4))
        etPin4.addTextChangedListener(PinTextWatcher(etPin4, etPin5))
        etPin5.addTextChangedListener(PinTextWatcher(etPin5, etPin6))
        etPin6.addTextChangedListener(PinTextWatcher(etPin6, null))
    }

    inner class PinTextWatcher(private val currentEditText: EditText, private val nextEditText: EditText?) :
        TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s?.length == 1) {
                nextEditText?.requestFocus()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}