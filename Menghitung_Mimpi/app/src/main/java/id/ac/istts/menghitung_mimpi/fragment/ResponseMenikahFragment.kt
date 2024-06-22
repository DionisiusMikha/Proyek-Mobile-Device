package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

class ResponseMenikahFragment : Fragment() {
    lateinit var tvResponseTextMenikah: TextView
    lateinit var tvTotalUangResponseMenikah: TextView
    lateinit var tvUangSaatIniResponseMenikah: TextView
    lateinit var tvJumlahInvestasiResponseMenikah: TextView
    lateinit var tcReturnInvestasiResponseMenikah: TextView
    lateinit var tvLamaInvestasiResponseMenikah: TextView
    lateinit var tvHasilInvestasiResponseMenikah: TextView
    lateinit var btnSimpanResponseMenikah: Button
    lateinit var ivResponseEmoteMenikah: ImageView

    private val vm: SavingVM by activityViewModels {
        SavingFactory(SavingRepo(RetrofitInstance.apiSave))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_response_menikah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvResponseTextMenikah = view.findViewById(R.id.tvResponseTextMenikah)
        tvTotalUangResponseMenikah = view.findViewById(R.id.tvTotalUangResponseMenikah)
        tvUangSaatIniResponseMenikah = view.findViewById(R.id.tvUangSaatIniResponseMenikah)
        tvJumlahInvestasiResponseMenikah = view.findViewById(R.id.tvJumlahInvestasiResponseMenikah)
        tcReturnInvestasiResponseMenikah = view.findViewById(R.id.tcReturnInvestasiResponseMenikah)
        tvLamaInvestasiResponseMenikah = view.findViewById(R.id.tvLamaInvestasiResponseMenikah)
        tvHasilInvestasiResponseMenikah = view.findViewById(R.id.tvHasilInvestasiResponseMenikah)
        btnSimpanResponseMenikah = view.findViewById(R.id.btnSimpanResponseMenikah)
        ivResponseEmoteMenikah = view.findViewById(R.id.ivResponseEmoteMenikah)
        btnSimpanResponseMenikah = view.findViewById(R.id.btnSimpanResponseMenikah)

        val bundle = arguments
        val biayaFinal = bundle?.getInt("biayaFinal")
        val uangSkrg = bundle?.getInt("uangSkrg")
        val invest = bundle?.getInt("invest")
        val presentase = bundle?.getInt("presentase")
        val waktu = bundle?.getInt("waktu")
        val totalFinal = bundle?.getInt("totalFinal")
        val status: Boolean? = bundle?.getBoolean("status")

        val formatter = DecimalFormat("#,###")
        if(status!!){
            tvResponseTextMenikah.text = "Strateginya cocok untuk mencapai mimpimu! \uD83D\uDE03\uD83C\uDF89"
            ivResponseEmoteMenikah.setImageResource(R.drawable.loan_1)
        } else{
            tvResponseTextMenikah.text = "Strateginya belum cocok untuk mencapai mimpi kamu \uD83D\uDE1E"
            ivResponseEmoteMenikah.setImageResource(R.drawable.loan_2)
        }
        tvTotalUangResponseMenikah.text = "Rp${formatter.format(biayaFinal)}"
        tvUangSaatIniResponseMenikah.text = "Rp${formatter.format(uangSkrg)}"
        tvJumlahInvestasiResponseMenikah.text = "Rp${formatter.format(invest)} / bulan"
        tcReturnInvestasiResponseMenikah.text = "${formatter.format(presentase)}% / tahun"
        tvLamaInvestasiResponseMenikah.text = "${formatter.format(waktu)} tahun"
        tvHasilInvestasiResponseMenikah.text = "Rp${formatter.format(totalFinal)}"

        btnSimpanResponseMenikah.setOnClickListener {
            coroutine.launch {
                vm.saveNikah(Token.getToken()!!, biayaFinal!!, uangSkrg!!, invest!!, presentase!!, waktu!!, totalFinal!!, status, onSuccess = { message ->
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