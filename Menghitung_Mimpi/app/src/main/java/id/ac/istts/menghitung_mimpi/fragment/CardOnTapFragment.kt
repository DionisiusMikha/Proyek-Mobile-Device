package id.ac.istts.menghitung_mimpi.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.layout.AddPocket
import id.ac.istts.menghitung_mimpi.layout.TopupActivity
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.UsersDataclass
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.UsersDataService
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CardOnTapFragment : Fragment() {

    lateinit var btnTopUp1: ImageButton
    lateinit var btnTopUp2: TextView
    lateinit var btnHistory1: ImageButton
    lateinit var btnHistory2: TextView
    lateinit var btnAddPocket1: ImageButton
    lateinit var btnAddPocket2: TextView
    lateinit var tvMainPocket: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_on_tap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnTopUp1 = view.findViewById(R.id.btnTopup1)
        btnTopUp2 = view.findViewById(R.id.btnTopup2)
        btnHistory1 = view.findViewById(R.id.btnHistory1)
        btnHistory2 = view.findViewById(R.id.btnHistory2)
        btnAddPocket1 = view.findViewById(R.id.btnAddPocket1)
        btnAddPocket2 = view.findViewById(R.id.btnAddPocket2)

        tvMainPocket = view.findViewById(R.id.tvMainPocket)

        val token = Token.getToken()
        if (token == null) {
            Toast.makeText(activity, "Invalid session", Toast.LENGTH_SHORT).show()
            return
        }

        btnTopUp1.setOnClickListener {
            val intent = Intent(activity, TopupActivity::class.java)
            startActivity(intent)
        }
        btnTopUp2.setOnClickListener {
            val intent = Intent(activity, TopupActivity::class.java)
            startActivity(intent)
        }

        getUsersData(token)

        btnAddPocket1.setOnClickListener {
            val intent = Intent(activity, AddPocket::class.java)
            startActivity(intent)
        }
        btnAddPocket2.setOnClickListener {
            val intent = Intent(activity, AddPocket::class.java)
            startActivity(intent)
        }
    }

    private fun formatRupiah(amount: Int): String {
        val formattedAmount = StringBuilder()
        val amountString = amount.toString()
        val amountLength = amountString.length
        var counter = 0
        for (i in amountLength - 1 downTo 0) {
            formattedAmount.insert(0, amountString[i])
            counter++
            if (counter % 3 == 0 && i != 0) {
                formattedAmount.insert(0, ".")
            }
        }
        return "Rp. $formattedAmount"
    }

    private fun getUsersData(token: String) {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit =
                Retrofit.Builder()
                        // .baseUrl("http://10.0.2.2:3000/api/")
                        .baseUrl("https://mdp.jensgelato.com//api/")
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .build()

        val usersDataService = retrofit.create(UsersDataService::class.java)

        usersDataService
                .getUserData("Bearer $token")
                .enqueue(
                        object : Callback<UsersDataclass> {
                            override fun onResponse(
                                    call: Call<UsersDataclass>,
                                    response: Response<UsersDataclass>
                            ) {
                                Log.d("Response", response.toString())
                                if (response.isSuccessful) {
                                    val usersData = response.body()
                                    if (usersData != null) {
                                        Toast.makeText(
                                                        activity,
                                                        "saldo: ${usersData.saldo}",
                                                        Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        //                        tvMainPocket.text = "Rp.
                                        // ${usersData.saldo}"
                                        tvMainPocket.text = formatRupiah(usersData.saldo)
                                    }
                                } else {
                                    Toast.makeText(
                                                    activity,
                                                    "Error: ${response.message()}",
                                                    Toast.LENGTH_SHORT
                                            )
                                            .show()
                                }
                            }

                            override fun onFailure(call: Call<UsersDataclass>, t: Throwable) {
                                Log.e("Error", t.message.toString())
                                Toast.makeText(activity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                                        .show()
                            }
                        }
                )
    }
}
