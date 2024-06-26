package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.KalkulatorVM
import java.text.DecimalFormat
import java.text.NumberFormat

class KalkulatorInvestasiFragment : Fragment() {
    lateinit var etUangYangInginDicapai: EditText
    lateinit var etWaktuMengumpulkanUang: EditText
    lateinit var etUangSaatIniKalkulatorInvestasi: EditText
    lateinit var etInvestasi: EditText
    lateinit var etReturProdukKalkulatorInvestasi: EditText
    lateinit var btnSetiapTahun: Button
    lateinit var btnSetiapBulan: Button
    lateinit var btnAwalTahun: Button
    lateinit var btnAkhirBulan: Button
    lateinit var btnHitungKalkulatorInvestasi: Button

    var type: Int = -1
    var range: Int = -1

    private val vm: KalkulatorVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kalkulator_investasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etUangYangInginDicapai = view.findViewById(R.id.etUangYangInginDicapai)
        etWaktuMengumpulkanUang = view.findViewById(R.id.etWaktuMengumpulkanUang)
        etUangSaatIniKalkulatorInvestasi = view.findViewById(R.id.etUangSaatIniKalkulatorInvestasi)
        etReturProdukKalkulatorInvestasi = view.findViewById(R.id.etReturProdukKalkulatorInvestasi)
        btnSetiapTahun = view.findViewById(R.id.btnSetiapTahun)
        btnSetiapBulan = view.findViewById(R.id.btnSetiapBulan)
        btnAwalTahun = view.findViewById(R.id.btnAwalTahun)
        btnAkhirBulan = view.findViewById(R.id.btnAkhirBulan)
        btnHitungKalkulatorInvestasi = view.findViewById(R.id.btnHitungKalkulatorInvestasi)

        etUangYangInginDicapai.addTextChangedListener(object : TextWatcher {
            private var current: String? = ""
            private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etUangYangInginDicapai.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                    if (cleanString.isNotEmpty()) {
                        try {
                            val parsed = cleanString.toDouble()
                            val formatted = currencyFormatter.format(parsed)

                            current = formatted
                            etUangYangInginDicapai.setText(formatted)
                            etUangYangInginDicapai.setSelection(formatted.length)
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }
                    } else {
                        current = ""
                        etUangYangInginDicapai.setText("")
                        etUangYangInginDicapai.setSelection(0)
                    }

                    etUangYangInginDicapai.addTextChangedListener(this)
                }
            }
        })
        etUangSaatIniKalkulatorInvestasi.addTextChangedListener(object : TextWatcher {
            private var current: String? = ""
            private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etUangSaatIniKalkulatorInvestasi.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                    if (cleanString.isNotEmpty()) {
                        try {
                            val parsed = cleanString.toDouble()
                            val formatted = currencyFormatter.format(parsed)

                            current = formatted
                            etUangSaatIniKalkulatorInvestasi.setText(formatted)
                            etUangSaatIniKalkulatorInvestasi.setSelection(formatted.length)
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }
                    } else {
                        current = ""
                        etUangSaatIniKalkulatorInvestasi.setText("")
                        etUangSaatIniKalkulatorInvestasi.setSelection(0)
                    }

                    etUangSaatIniKalkulatorInvestasi.addTextChangedListener(this)
                }
            }
        })

        btnSetiapTahun.setOnClickListener{
            type = 0
            btnAwalTahun.text = "Awal Tahun"
            btnAkhirBulan.text = "Akhir Tahun"
            if(type == 0){
                btnSetiapTahun.backgroundTintList = resources.getColorStateList(R.color.gray)
                btnSetiapBulan.backgroundTintList = resources.getColorStateList(R.color.polka)
                btnSetiapBulan.isEnabled = true
            }
        }
        btnSetiapBulan.setOnClickListener{
            type = 1
            btnAwalTahun.text = "Awal Bulan"
            btnAkhirBulan.text = "Akhir Bulan"
            if (type == 1){
                btnSetiapBulan.backgroundTintList = resources.getColorStateList(R.color.gray)
                btnSetiapTahun.backgroundTintList = resources.getColorStateList(R.color.polka)
                btnSetiapTahun.isEnabled = true
            }
        }
        btnAwalTahun.setOnClickListener{
            range = 0
            if (range == 0){
                btnAwalTahun.backgroundTintList = resources.getColorStateList(R.color.gray)
                btnAkhirBulan.backgroundTintList = resources.getColorStateList(R.color.polka)
            }
        }
        btnAkhirBulan.setOnClickListener{
            range = 1
            if (range == 1){
                btnAkhirBulan.backgroundTintList = resources.getColorStateList(R.color.gray)
                btnAwalTahun.backgroundTintList = resources.getColorStateList(R.color.polka)
            }
        }

        btnHitungKalkulatorInvestasi.setOnClickListener{
            val target: Int = etUangYangInginDicapai.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val waktu: Int = etWaktuMengumpulkanUang.text.toString().toInt()
            val uangSkrg: Int = etUangSaatIniKalkulatorInvestasi.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val presentase: Int = etReturProdukKalkulatorInvestasi.text.toString().toInt()
            vm.calculate(target, waktu, uangSkrg, type, range, 100000, presentase, onSuccess = { target, uangSkrg, invest, presentase, waktu, final, res ->
                activity?.runOnUiThread {
                    val bundle = Bundle()
                    bundle.putInt("type", type)
                    bundle.putInt("target", target)
                    bundle.putInt("uangSkrg", uangSkrg)
                    bundle.putInt("invest", invest)
                    bundle.putInt("presentase", presentase)
                    bundle.putInt("waktu", waktu)
                    bundle.putInt("final", final)
                    bundle.putBoolean("res", res)
                    goToFragment(ResponseKalkulatorInvestasiFragment(), bundle)
                }
            }, onFailed = { error ->
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun goToFragment(fragment: Fragment, bundle: Bundle) {
        fragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}