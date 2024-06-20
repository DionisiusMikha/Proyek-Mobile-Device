package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.LoginFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.SavingFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.LoginRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.SavingRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.SavingVM
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class ResponseKalkulatorInvestasiFragment : Fragment() {
    lateinit var textView82: TextView
    lateinit var tvResponseTextKalkulatorInvestasi1: TextView
    lateinit var tvTotalUangDibutuhkanKalkulatorInvestasiResponse: TextView
    lateinit var tvUangSaatIniResponseKalkulatorInvestasi: TextView
    lateinit var tvJumlahInvestasiResponseKalkulatorInvestasi: TextView
    lateinit var tvReturnInvestasiResponseKalkulatorInvestasi: TextView
    lateinit var tvLamaInvestasiResponseKalkulatorInvestasi: TextView
    lateinit var tvHasilInvestasiResponseKalkulatorInvestasi: TextView
    lateinit var ivResponseEmoteKalkulatorInvestasi: ImageView
    lateinit var btnSimpanResponseKalkulatorInvestasi: Button

    private val vm: SavingVM by activityViewModels {
        SavingFactory(SavingRepo(RetrofitInstance.apiSave))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_response_kalkulator_infestasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView82 = view.findViewById(R.id.textView82)
        tvResponseTextKalkulatorInvestasi1 = view.findViewById(R.id.tvResponseTextKalkulatorInvestasi1)
        tvTotalUangDibutuhkanKalkulatorInvestasiResponse = view.findViewById(R.id.tvTotalUangDibutuhkanKalkulatorInvestasiResponse)
        tvUangSaatIniResponseKalkulatorInvestasi = view.findViewById(R.id.tvUangSaatIniResponseKalkulatorInvestasi)
        tvResponseTextKalkulatorInvestasi1 = view.findViewById(R.id.tvResponseTextKalkulatorInvestasi1)
        tvJumlahInvestasiResponseKalkulatorInvestasi = view.findViewById(R.id.tvJumlahInvestasiResponseKalkulatorInvestasi)
        tvReturnInvestasiResponseKalkulatorInvestasi = view.findViewById(R.id.tvReturnInvestasiResponseKalkulatorInvestasi)
        tvLamaInvestasiResponseKalkulatorInvestasi = view.findViewById(R.id.tvLamaInvestasiResponseKalkulatorInvestasi)
        tvHasilInvestasiResponseKalkulatorInvestasi = view.findViewById(R.id.tvHasilInvestasiResponseKalkulatorInvestasi)
        ivResponseEmoteKalkulatorInvestasi = view.findViewById(R.id.ivResponseEmoteKalkulatorInvestasi)
        btnSimpanResponseKalkulatorInvestasi = view.findViewById(R.id.btnSimpanResponseKalkulatorInvestasi)

        val bundle = arguments
        val type = bundle?.getInt("type")
        val target = bundle?.getInt("target")
        val uangSkrg = bundle?.getInt("uangSkrg")
        val invest = bundle?.getInt("invest")
        val presentase = bundle?.getInt("presentase")
        val waktu = bundle?.getInt("waktu")
        val final = bundle?.getInt("final")
        val res: Boolean? = bundle?.getBoolean("res")

        val formatter = DecimalFormat("#,###")
        if(type == 1) textView82.text = "Jumlah investasi / bulan" else textView82.text = "Jumlah investasi / tahun"
        if(res!!){
            tvResponseTextKalkulatorInvestasi1.text = "Strateginya cocok untuk mencapai mimpimu! \uD83D\uDE03\uD83C\uDF89"
            ivResponseEmoteKalkulatorInvestasi.setImageResource(R.drawable.loan_1)
        } else{
            tvResponseTextKalkulatorInvestasi1.text = "Strateginya belum cocok untuk mencapai mimpi kamu \uD83D\uDE1E"
            ivResponseEmoteKalkulatorInvestasi.setImageResource(R.drawable.loan_2)
        }
        tvTotalUangDibutuhkanKalkulatorInvestasiResponse.text = "Rp${formatter.format(target)}"
        tvUangSaatIniResponseKalkulatorInvestasi.text = "Rp${formatter.format(uangSkrg)}"
        tvJumlahInvestasiResponseKalkulatorInvestasi.text = "Rp${formatter.format(invest)}"
        tvReturnInvestasiResponseKalkulatorInvestasi.text = "${formatter.format(presentase)}% / tahun"
        tvLamaInvestasiResponseKalkulatorInvestasi.text = "${formatter.format(waktu)} tahun"
        tvHasilInvestasiResponseKalkulatorInvestasi.text = "Rp${formatter.format(final)}"

        btnSimpanResponseKalkulatorInvestasi.setOnClickListener{
            coroutine.launch {
                vm.saveInvest(Token.getToken()!!, target!!, waktu!!, uangSkrg!!, invest!!, presentase!!, final!!, type!!, res, onSuccess = { message ->
                    activity?.runOnUiThread{
                        Toast.makeText(requireContext(), "Berhasil menyimpan!!", Toast.LENGTH_SHORT).show()
                        goToFragment(HomeFragment())
                    }
                }, onError = { error ->
                    activity?.runOnUiThread{
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun goToFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}