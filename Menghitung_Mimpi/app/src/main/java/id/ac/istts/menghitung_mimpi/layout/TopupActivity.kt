package id.ac.istts.menghitung_mimpi.layout

import android.os.Bundle
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
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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

        val token = Token.getToken()

        if (token == null) {
            Toast.makeText(this, "Error: Token is null", Toast.LENGTH_SHORT).show()
            return
        }

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit =
                Retrofit.Builder()
                        .baseUrl("https://mdp.jensgelato.com/api/pocket/")
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .build()

        val topupService = retrofit.create(TopupService::class.java)

        fetchTopupItems(topupService, token)

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

    private fun fetchTopupItems(service: TopupService, token: String) {
        service.getTopupItems("Bearer $token")
                .enqueue(
                        object : Callback<List<PocketItem>> {
                            override fun onResponse(
                                    call: Call<List<PocketItem>>,
                                    response: Response<List<PocketItem>>
                            ) {
                                if (response.isSuccessful) {
                                    val topupItems = response.body() ?: emptyList()
                                    val itemNames = topupItems.map { it.nama_pocket }
                                    val adapter =
                                            ArrayAdapter(
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
                                            )
                                            .show()
                                }
                            }

                            override fun onFailure(call: Call<List<PocketItem>>, t: Throwable) {
                                Toast.makeText(
                                                this@TopupActivity,
                                                "Error: ${t.message}",
                                                Toast.LENGTH_SHORT
                                        )
                                        .show()
                            }
                        }
                )
    }
}
