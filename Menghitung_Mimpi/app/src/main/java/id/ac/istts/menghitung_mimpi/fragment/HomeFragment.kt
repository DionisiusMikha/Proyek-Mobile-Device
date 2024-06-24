package id.ac.istts.menghitung_mimpi.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.layout.ChatBot
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.AuthFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.AuthRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.AuthVM
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.util.Calendar

class HomeFragment : Fragment() {

    lateinit var tvUser: TextView
    lateinit var tvSaldo: TextView
    lateinit var tvTotalTopup: TextView
    lateinit var btn_calcInves: CardView
    lateinit var btn_SpendAnalysis: CardView
    lateinit var btn_DanaDarurat: CardView
    lateinit var btn_Menikah: CardView
    lateinit var btn_TanyaPenggu: CardView

    private val vm: AuthVM by activityViewModels {
        AuthFactory(AuthRepo(RetrofitInstance.apiAuth))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvUser = view.findViewById(R.id.tvUser)
        tvSaldo = view.findViewById(R.id.tvSaldo)
        tvTotalTopup = view.findViewById(R.id.tvTotalTopup)
        btn_DanaDarurat = view.findViewById(R.id.btn_DanaDarurat)
        btn_TanyaPenggu = view.findViewById(R.id.btn_TanyaPenggu)
        btn_Menikah = view.findViewById(R.id.btn_Menikah)
        btn_SpendAnalysis = view.findViewById(R.id.btn_SpendAnalysis)
        btn_calcInves = view.findViewById(R.id.btn_calcInvest)

        val formatter = DecimalFormat("#,###")
        coroutine.launch {
            vm.getName(Token.getToken()!!, onSuccess = { name, saldo, tabungan, _ ->
                activity?.runOnUiThread {
                    tvUser.text = name
                    tvSaldo.text = "Rp. ${formatter.format(saldo)}"
                    tvTotalTopup.text = "Kamu sudah menabung Sebanyak Rp. ${formatter.format(tabungan)} bulan ini"
                }
            }, onError = { error ->
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }
            })
        }

        btn_DanaDarurat.setOnClickListener {
            findNavController().navigate(R.id.action_global_danaDaruratFragment)
        }

        btn_calcInves.setOnClickListener {
            findNavController().navigate(R.id.action_global_kalkulatorInvestasiFragment)
        }

        btn_Menikah.setOnClickListener {
            findNavController().navigate(R.id.action_global_pernikahanFragment)
        }

        btn_SpendAnalysis.setOnClickListener {
            Toast.makeText(requireContext(), "Coming Soon", Toast.LENGTH_LONG).show()
        }

        btn_TanyaPenggu.setOnClickListener {
            val intent = Intent(requireActivity(), ChatBot::class.java)
            startActivity(intent)
        }
    }
}
