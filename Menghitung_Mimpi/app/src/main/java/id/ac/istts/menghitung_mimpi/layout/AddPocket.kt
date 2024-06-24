package id.ac.istts.menghitung_mimpi.layout

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.UsersDataclass
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.UpdatePocketRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.UsersDataService
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AddPocket : AppCompatActivity() {

    lateinit var etPocketName: EditText
    lateinit var btnSaveAddPocket: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pocket)

        etPocketName = findViewById(R.id.etPocketName)
        btnSaveAddPocket = findViewById(R.id.btnSaveAddPocket)

        val token = Token.getToken()

        if (token == null) {
            Toast.makeText(this, "Error: Token is null", Toast.LENGTH_SHORT).show()
            return
        }

        getUsersData(token)
    }

    private fun getUsersData(token: String) {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            // .baseUrl("http://10.0.2.2:3000/api/")
            .baseUrl("https://mdp.jensgelato.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val usersDataService = retrofit.create(UsersDataService::class.java)

        usersDataService.getUserData("Bearer $token").enqueue(object : Callback<UsersDataclass> {
            override fun onResponse(
                call: Call<UsersDataclass>,
                response: Response<UsersDataclass>
            ) {
                if (response.isSuccessful) {
                    val usersData = response.body()
                    if (usersData != null) {
                        Log.d("Response", usersData.toString())
                        val idUser = usersData.id_user

                        handleUserID(idUser)
                        Toast.makeText(this@AddPocket, "Welcome, ${usersData.full_name}", Toast.LENGTH_SHORT).show()

                        btnSaveAddPocket.setOnClickListener {
                            val pocketName = etPocketName.text.toString()
                            if (pocketName.isEmpty()) {
                                Toast.makeText(this@AddPocket, "Error: Pocket name is empty", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }

                            val request = UpdatePocketRequest(pocketName, 0)
                            usersDataService.create("Bearer $token", request).enqueue(object : Callback<Void> {
                                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(this@AddPocket, "Pocket added successfully", Toast.LENGTH_SHORT).show()
                                        finish()
                                    } else {
                                        Log.e("Error", response.toString())
                                        Toast.makeText(this@AddPocket, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    Log.e("Error", t.message.toString())
                                    Toast.makeText(this@AddPocket, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }

                    } else {
                        Log.e("Error", "User data is null")
                        Toast.makeText(this@AddPocket, "Error: User data is null", Toast.LENGTH_SHORT)
                            .show()


                    }
                } else {
                    Log.e("Error", response.message())
                    Toast.makeText(this@AddPocket, "Error: ${response.message()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<UsersDataclass>, t: Throwable) {
                Log.e("Error", t.message.toString())
                Toast.makeText(this@AddPocket, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleUserID(idUser: String) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userId", idUser)
        editor.apply()
    }
}