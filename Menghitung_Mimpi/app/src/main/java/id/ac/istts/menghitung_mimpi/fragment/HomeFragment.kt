package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.AuthFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.LoginFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.AuthRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.LoginRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.AuthVM
import id.ac.istts.menghitung_mimpi.viewmodel.LoginVM
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeFragment : Fragment() {

    lateinit var tvUser: TextView
    lateinit var btn_calcInves: Button
    lateinit var btn_SpendAnalysis: Button
    lateinit var btn_DanaDarurat: Button
    lateinit var btn_Menikah: Button
    lateinit var btn_TanyaPenggu: Button

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
        btn_DanaDarurat = view.findViewById(R.id.btn_DanaDarurat)
        btn_TanyaPenggu = view.findViewById(R.id.btn_TanyaPenggu)
        btn_Menikah = view.findViewById(R.id.btn_Menikah)
        btn_SpendAnalysis = view.findViewById(R.id.btn_SpendAnalysis)
        btn_calcInves = view.findViewById(R.id.btn_calcInvest)
//        val c = Calendar.getInstance()
//        val timeOfDay = c.get(Calendar.HOUR_OF_DAY)
//
//        if (timeOfDay >= 0 && timeOfDay < 12) {
//            timeTV.text = "Good Morning"
//        } else if (timeOfDay >= 12 && timeOfDay < 16) {
//            timeTV.text = "Good Afternoon"
//        } else if (timeOfDay >= 16 && timeOfDay < 21) {
//            timeTV.text = "Good Evening"
//        } else if (timeOfDay >= 21 && timeOfDay < 24) {
//            timeTV.text = "Good Night"
//        }
        coroutine.launch {
            vm.getName(Token.getToken()!!, onSuccess = { name ->
                activity?.runOnUiThread {
                    tvUser.text = name
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
            findNavController().navigate(R.id.action_global_spendAnalysisFragment)
        }

        btn_TanyaPenggu.setOnClickListener {
            findNavController().navigate(R.id.action_global_chatBotFragment)
        }
    }
}
