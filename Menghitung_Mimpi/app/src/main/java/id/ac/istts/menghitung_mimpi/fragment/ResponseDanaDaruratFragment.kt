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
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.SavingFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.SavingRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.SavingVM
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class ResponseDanaDaruratFragment : Fragment() {
    lateinit var tvResponseTextDanaDarurat: TextView
    lateinit var tvDanaDaruratYangDibutuhkan: TextView
    lateinit var tvDanaDaruratSaatIni: TextView
    lateinit var tvJumlahInvestasiResponseDanaDarurat: TextView
    lateinit var tvReturnInvestasiResponseDanaDarurat: TextView
    lateinit var tvLamaInvestasiResponseDanaDarurat: TextView
    lateinit var tvHasilInvestasiResponseDanaDarurat: TextView
    lateinit var ivResponseEmoteDanaDarurat: ImageView
    lateinit var btnSimpanRespnseDanaDarurat: Button

    private val vm: SavingVM by activityViewModels {
        SavingFactory(SavingRepo(RetrofitInstance.apiSave))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_response_dana_darurat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvResponseTextDanaDarurat = view.findViewById(R.id.tvResponseTextDanaDarurat)
        tvDanaDaruratYangDibutuhkan = view.findViewById(R.id.tvDanaDaruratYangDibutuhkan)
        tvDanaDaruratSaatIni = view.findViewById(R.id.tvDanaDaruratSaatIni)
        tvJumlahInvestasiResponseDanaDarurat = view.findViewById(R.id.tvJumlahInvestasiResponseDanaDarurat)
        tvReturnInvestasiResponseDanaDarurat = view.findViewById(R.id.tvReturnInvestasiResponseDanaDarurat)
        tvLamaInvestasiResponseDanaDarurat = view.findViewById(R.id.tvLamaInvestasiResponseDanaDarurat)
        tvHasilInvestasiResponseDanaDarurat = view.findViewById(R.id.tvHasilInvestasiResponseDanaDarurat)
        ivResponseEmoteDanaDarurat = view.findViewById(R.id.ivResponseEmoteDanaDarurat)
        btnSimpanRespnseDanaDarurat = view.findViewById(R.id.btnSimpanRespnseDanaDarurat)

        val bundle = arguments
        val danaDarurat = bundle?.getInt("danaDarurat")
        val danaSkrg = bundle?.getInt("danaSkrg")
        val lama = bundle?.getInt("lama")
        val invest = bundle?.getInt("invest")
        val presentase =  bundle?.getInt("presentase")
        val total = bundle?.getInt("total")
        val status: Boolean? = bundle?.getBoolean("status")
        val simpan: Boolean? = bundle?.getBoolean("simpan")

        if(simpan == true){
            btnSimpanRespnseDanaDarurat.isVisible = false
        }

        val formatter = DecimalFormat("#,###")
        if(status!!){
            tvResponseTextDanaDarurat.text = "Strateginya cocok untuk mencapai mimpimu! \uD83D\uDE03\uD83C\uDF89"
            ivResponseEmoteDanaDarurat.setImageResource(R.drawable.loan_1)
        } else{
            tvResponseTextDanaDarurat.text = "Strateginya belum cocok untuk mencapai mimpi kamu \uD83D\uDE1E"
            ivResponseEmoteDanaDarurat.setImageResource(R.drawable.loan_2)
        }
        tvDanaDaruratYangDibutuhkan.text = "Rp${formatter.format(danaDarurat)}"
        tvDanaDaruratSaatIni.text = "Rp${formatter.format(danaSkrg)}"
        tvJumlahInvestasiResponseDanaDarurat.text = "Rp${formatter.format(invest)}"
        tvReturnInvestasiResponseDanaDarurat.text = "${presentase}% / tahun"
        tvLamaInvestasiResponseDanaDarurat.text = "$lama bulan"
        tvHasilInvestasiResponseDanaDarurat.text = "Rp${formatter.format(total)}"

        btnSimpanRespnseDanaDarurat.setOnClickListener {
            coroutine.launch {
                vm.saveDanaDarurat(Token.getToken()!!, danaDarurat!!, danaSkrg!!, lama!!, invest!!, presentase!!, total!!, status, onSuccess = { message ->
                    activity?.runOnUiThread{
                        Toast.makeText(requireContext(), "Berhasil menyimpan!!", Toast.LENGTH_SHORT).show()
                        popBackStackTwice()
                    }
                }, onError = { error ->
                    activity?.runOnUiThread{
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun popBackStackTwice() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStackImmediate() // First pop
        fragmentManager.popBackStackImmediate() // Second pop
    }
}