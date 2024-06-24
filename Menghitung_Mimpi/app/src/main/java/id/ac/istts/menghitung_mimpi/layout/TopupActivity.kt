package id.ac.istts.menghitung_mimpi.layout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.ac.istts.menghitung_mimpi.R

class TopupActivity : AppCompatActivity() {

    lateinit var btnSaveTopup: Button
    lateinit var dropdownMetode: Spinner
    lateinit var dropdownPocket: Spinner
    lateinit var etAmount: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)

        btnSaveTopup = findViewById(R.id.btnSaveTopup)
        dropdownMetode = findViewById(R.id.dropdownMetode)
        dropdownPocket = findViewById(R.id.dropdownPocket)
        etAmount = findViewById(R.id.etAmount)

        btnSaveTopup.setOnClickListener {
            val metode = dropdownMetode.selectedItem.toString()
            val pocket = dropdownPocket.selectedItem.toString()
            val amount = etAmount.text.toString()

            if (metode == "Pilih Metode") {
                Toast.makeText(this, "Pilih Metode", Toast.LENGTH_SHORT).show()
            } else if (pocket == "Pilih Pocket") {
                Toast.makeText(this, "Pilih Pocket", Toast.LENGTH_SHORT).show()
            } else if (amount.isEmpty()) {
                Toast.makeText(this, "Masukkan Jumlah", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Topup Berhasil", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
