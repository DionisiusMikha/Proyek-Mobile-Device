package id.ac.istts.menghitung_mimpi.layout

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.PocketItem
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.TopupService
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.UpdatePocketRequest
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.NumberFormat
import java.util.Locale

class TopupActivity : AppCompatActivity() {

    lateinit var btnSaveTopup: Button
    lateinit var dropdownMetode: Spinner
    lateinit var dropdownPocket: Spinner
    lateinit var etAmount: EditText

    private var pocketItems: MutableList<PocketItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)

        btnSaveTopup = findViewById(R.id.btnSaveTopup)
        dropdownMetode = findViewById(R.id.dropdownMetode)
        dropdownPocket = findViewById(R.id.dropdownPocket)
        etAmount = findViewById(R.id.etAmount)

        val token = Token.getToken()

        if (token == null) {
            Toast.makeText(this, "Error: Token is null", Toast.LENGTH_SHORT).show()
            return
        }

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit =
            Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/api/pocket/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        val topupService = retrofit.create(TopupService::class.java)

        fetchTopupItems(topupService, token)

        val metodeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("Admin")
        )
        metodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdownMetode.adapter = metodeAdapter
        dropdownMetode.setSelection(0)

        val pocketAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("Pilih Pocket")
        )
        pocketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdownPocket.adapter = pocketAdapter
        dropdownPocket.setSelection(0)

        btnSaveTopup.setOnClickListener {
            val amount = etAmount.text.toString()
            val pocket = dropdownPocket.selectedItem.toString()
            val metode = dropdownMetode.selectedItem.toString()

            if (amount.isEmpty()) {
                Toast.makeText(this, "Masukkan Jumlah", Toast.LENGTH_SHORT).show()
            } else if (pocket == "Pilih Pocket") {
                Toast.makeText(this, "Pilih Pocket", Toast.LENGTH_SHORT).show()
            } else {
                val amountInt = amount.replace("[,.]".toRegex(), "").toInt()
                if (pocketItems.isNotEmpty()) {
                    val selectedPocketId = getPocketIdByName(pocket)
                    val request = UpdatePocketRequest(nama_pocket = pocket, saldo_pocket = amountInt)
                    if (selectedPocketId != null) {
                        topupService.updatePocket(selectedPocketId, "Bearer $token", request)
                            .enqueue(object : Callback<Void> {
                                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(
                                            this@TopupActivity,
                                            "Topup Berhasil",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Log.e("Topup custom", response.toString())
                                        Toast.makeText(
                                            this@TopupActivity,
                                            "Topup Gagal: ${response.message()}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    Toast.makeText(
                                        this@TopupActivity,
                                        "Error: ${t.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    } else {
                        Toast.makeText(this, "Error: Pocket ID is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle case where pocketItems is not yet initialized or empty
                    Toast.makeText(this, "Error: Pocket Items belum siap", Toast.LENGTH_SHORT).show()
                }
            }
        }

        etAmount.addTextChangedListener(object : TextWatcher {
            private var current = ""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etAmount.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[,.]".toRegex(), "")
                    val parsed = cleanString.toDoubleOrNull()
                    val formatted = if (parsed != null) {
                        NumberFormat.getNumberInstance(Locale.US).format(parsed)
                    } else {
                        ""
                    }

                    current = formatted
                    etAmount.setText(formatted)
                    etAmount.setSelection(formatted.length)

                    etAmount.addTextChangedListener(this)
                }
            }
        })
    }

    private fun fetchTopupItems(service: TopupService, token: String) {
        service.getTopupItems("Bearer $token")
            .enqueue(
                object : Callback<List<PocketItem>> {
                    override fun onResponse(
                        call: Call<List<PocketItem>>,
                        response: Response<List<PocketItem>>
                    ) {
                        if (response.isSuccessful) {
                            pocketItems.clear()  // Clear existing items
                            pocketItems.addAll(response.body() ?: emptyList())

                            // Update the dropdown adapter with fetched data
                            val itemNames = pocketItems.map { it.nama_pocket }
                            val adapter = ArrayAdapter(
                                this@TopupActivity,
                                android.R.layout.simple_spinner_item,
                                itemNames
                            )
                            adapter.setDropDownViewResource(
                                android.R.layout.simple_spinner_dropdown_item
                            )
                            dropdownPocket.adapter = adapter
                        } else {
                            Log.e("TopupActivity", response.toString())
                            Toast.makeText(
                                this@TopupActivity,
                                "Failed to load data due to ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<List<PocketItem>>, t: Throwable) {
                        Log.e("TopupActivity", t.message.toString())
                        Toast.makeText(
                            this@TopupActivity,
                            "Error: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
    }

    private fun getPocketIdByName(name: String): String? {
        val pocketItem = pocketItems.find { it.nama_pocket == name }
        return pocketItem?.id_pocket
    }

}
