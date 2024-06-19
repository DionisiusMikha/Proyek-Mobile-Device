package id.ac.istts.menghitung_mimpi.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.ac.istts.menghitung_mimpi.R
import java.text.DecimalFormat

class ResponseKalkulatorInvestasiFragment : Fragment() {
    lateinit var tvResponseTextKalkulatorInvestasi1: TextView
    lateinit var tvTotalUangDibutuhkanKalkulatorInvestasiResponse: TextView
    lateinit var tvUangSaatIniResponseKalkulatorInvestasi: TextView
    lateinit var tvJumlahInvestasiResponseKalkulatorInvestasi: TextView
    lateinit var tvReturnInvestasiResponseKalkulatorInvestasi: TextView
    lateinit var tvLamaInvestasiResponseKalkulatorInvestasi: TextView
    lateinit var tvHasilInvestasiResponseKalkulatorInvestasi: TextView
    lateinit var ivResponseEmoteKalkulatorInvestasi: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_response_kalkulator_infestasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvResponseTextKalkulatorInvestasi1 = view.findViewById(R.id.tvResponseTextKalkulatorInvestasi1)
        tvTotalUangDibutuhkanKalkulatorInvestasiResponse = view.findViewById(R.id.tvTotalUangDibutuhkanKalkulatorInvestasiResponse)
        tvUangSaatIniResponseKalkulatorInvestasi = view.findViewById(R.id.tvUangSaatIniResponseKalkulatorInvestasi)
        tvResponseTextKalkulatorInvestasi1 = view.findViewById(R.id.tvResponseTextKalkulatorInvestasi1)
        tvJumlahInvestasiResponseKalkulatorInvestasi = view.findViewById(R.id.tvJumlahInvestasiResponseKalkulatorInvestasi)
        tvReturnInvestasiResponseKalkulatorInvestasi = view.findViewById(R.id.tvReturnInvestasiResponseKalkulatorInvestasi)
        tvLamaInvestasiResponseKalkulatorInvestasi = view.findViewById(R.id.tvLamaInvestasiResponseKalkulatorInvestasi)
        tvHasilInvestasiResponseKalkulatorInvestasi = view.findViewById(R.id.tvHasilInvestasiResponseKalkulatorInvestasi)
        ivResponseEmoteKalkulatorInvestasi = view.findViewById(R.id.ivResponseEmoteKalkulatorInvestasi)

        val bundle = arguments
        val target = bundle?.getInt("target")
        val uangSkrg = bundle?.getInt("uangSkrg")
        val invest = bundle?.getInt("invest")
        val presentase = bundle?.getInt("presentase")
        val waktu = bundle?.getInt("waktu")
        val final = bundle?.getInt("final")
        val res: Boolean? = bundle?.getBoolean("res")

        val formatter = DecimalFormat("#,###")

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
        tvReturnInvestasiResponseKalkulatorInvestasi.text = formatter.format(presentase)
        tvLamaInvestasiResponseKalkulatorInvestasi.text = formatter.format(waktu)
        tvHasilInvestasiResponseKalkulatorInvestasi.text = "Rp${formatter.format(final)}"
    }
}