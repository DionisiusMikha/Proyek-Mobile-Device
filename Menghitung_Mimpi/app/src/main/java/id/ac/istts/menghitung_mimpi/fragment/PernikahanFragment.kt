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
import id.ac.istts.menghitung_mimpi.viewmodel.NikahVM
import java.text.DecimalFormat
import java.text.NumberFormat

class PernikahanFragment : Fragment() {
    lateinit var etTotalBiayaResepsi: EditText
    lateinit var etMenikahDalam: EditText
    lateinit var etAsumsiInflasi: EditText
    lateinit var etTotalUangYangDiperlukan: EditText
    lateinit var etUangSekarang: EditText
    lateinit var etTargetInvestasi: EditText
    lateinit var etProdukRetur: EditText
    lateinit var etLamaInvestasi: EditText
    lateinit var btnHitungMenikah: Button

    private val vm: NikahVM by activityViewModels()
    val formatter = DecimalFormat("#,###")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pernikahan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etTotalBiayaResepsi = view.findViewById(R.id.etTotalBiayaResepsi)
        etMenikahDalam = view.findViewById(R.id.etMenikahDalam)
        etAsumsiInflasi = view.findViewById(R.id.etAsumsiInflasi)
        etTotalUangYangDiperlukan = view.findViewById(R.id.etTotalUangYangDiperlukan)
        etUangSekarang = view.findViewById(R.id.etUangSekarang)
        etTargetInvestasi = view.findViewById(R.id.etTargetInvestasi)
        etProdukRetur = view.findViewById(R.id.etProdukRetur)
        etLamaInvestasi = view.findViewById(R.id.etLamaInvestasi)
        btnHitungMenikah = view.findViewById(R.id.btnHitungMenikah)

        etTotalBiayaResepsi.addTextChangedListener(object : TextWatcher {
                private var current: String? = ""
                private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString() != current) {
                        etTotalBiayaResepsi.removeTextChangedListener(this)

                        val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                        if (cleanString.isNotEmpty()) {
                            try {
                                val parsed = cleanString.toDouble()
                                val formatted = currencyFormatter.format(parsed)

                                current = formatted
                                etTotalBiayaResepsi.setText(formatted)
                                etTotalBiayaResepsi.setSelection(formatted.length)

                                hitungTotalBiaya()
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        } else {
                            current = ""
                            etTotalBiayaResepsi.setText("")
                            etTotalBiayaResepsi.setSelection(0)
                        }

                        etTotalBiayaResepsi.addTextChangedListener(this)
                    }
                }
            })
        etMenikahDalam.addTextChangedListener(object : TextWatcher {
                private var current: String? = ""
                private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString() != current) {
                        etMenikahDalam.removeTextChangedListener(this)
                        etLamaInvestasi.setText("${etMenikahDalam.text.toString()}")
                        val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                        if (cleanString.isNotEmpty()) {
                            try {
                                val parsed = cleanString.toDouble()
                                val formatted = currencyFormatter.format(parsed)

                                current = formatted
                                etMenikahDalam.setText(formatted)
                                etMenikahDalam.setSelection(formatted.length)

                                hitungTotalBiaya()
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        } else {
                            current = ""
                            etMenikahDalam.setText("")
                            etMenikahDalam.setSelection(0)
                        }

                        etMenikahDalam.addTextChangedListener(this)
                    }
                }
            })
        etAsumsiInflasi.addTextChangedListener(object : TextWatcher {
                private var current: String? = ""
                private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString() != current) {
                        etAsumsiInflasi.removeTextChangedListener(this)

                        val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                        if (cleanString.isNotEmpty()) {
                            try {
                                val parsed = cleanString.toDouble()
                                val formatted = currencyFormatter.format(parsed)

                                current = formatted
                                etAsumsiInflasi.setText(formatted)
                                etAsumsiInflasi.setSelection(formatted.length)

                                hitungTotalBiaya()
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        } else {
                            current = ""
                            etAsumsiInflasi.setText("")
                            etAsumsiInflasi.setSelection(0)
                        }

                        etAsumsiInflasi.addTextChangedListener(this)
                    }
                }
            })
        etUangSekarang.addTextChangedListener(object : TextWatcher {
                private var current: String? = ""
                private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString() != current) {
                        etUangSekarang.removeTextChangedListener(this)

                        val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                        if (cleanString.isNotEmpty()) {
                            try {
                                val parsed = cleanString.toDouble()
                                val formatted = currencyFormatter.format(parsed)

                                current = formatted
                                etUangSekarang.setText(formatted)
                                etUangSekarang.setSelection(formatted.length)
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        } else {
                            current = ""
                            etUangSekarang.setText("")
                            etUangSekarang.setSelection(0)
                        }

                        etUangSekarang.addTextChangedListener(this)
                    }
                }
            })
        etTargetInvestasi.addTextChangedListener(object : TextWatcher {
                private var current: String? = ""
                private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString() != current) {
                        etTargetInvestasi.removeTextChangedListener(this)

                        val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                        if (cleanString.isNotEmpty()) {
                            try {
                                val parsed = cleanString.toDouble()
                                val formatted = currencyFormatter.format(parsed)

                                current = formatted
                                etTargetInvestasi.setText(formatted)
                                etTargetInvestasi.setSelection(formatted.length)
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        } else {
                            current = ""
                            etTargetInvestasi.setText("")
                            etTargetInvestasi.setSelection(0)
                        }

                        etTargetInvestasi.addTextChangedListener(this)
                    }
                }
            })
        etProdukRetur.addTextChangedListener(object : TextWatcher {
                private var current: String? = ""
                private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString() != current) {
                        etProdukRetur.removeTextChangedListener(this)

                        val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                        if (cleanString.isNotEmpty()) {
                            try {
                                val parsed = cleanString.toDouble()
                                val formatted = currencyFormatter.format(parsed)

                                current = formatted
                                etProdukRetur.setText(formatted)
                                etProdukRetur.setSelection(formatted.length)
                            } catch (e: NumberFormatException) {
                                e.printStackTrace()
                            }
                        } else {
                            current = ""
                            etProdukRetur.setText("")
                            etProdukRetur.setSelection(0)
                        }

                        etProdukRetur.addTextChangedListener(this)
                    }
                }
            })

        btnHitungMenikah.setOnClickListener{
            val biaya: Int = etTotalBiayaResepsi.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val waktu: Int = etMenikahDalam.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val inflasi: Int = etAsumsiInflasi.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val uangSkrg: Int = etUangSekarang.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val invest: Int = etTargetInvestasi.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val presentase: Int = etProdukRetur.text.toString().replace("[Rp,.]".toRegex(), "").toInt()

            vm.calculate(biaya, waktu, inflasi, uangSkrg, invest, presentase, onSuccess = { biayaFinal, uangSkrg, invest, presentase, waktu, totalFinal, status ->
                activity?.runOnUiThread {
                    val bundle = Bundle()
                    bundle.putInt("biayaFinal", biayaFinal)
                    bundle.putInt("uangSkrg", uangSkrg)
                    bundle.putInt("invest", invest)
                    bundle.putInt("presentase", presentase)
                    bundle.putInt("waktu", waktu)
                    bundle.putInt("totalFinal", totalFinal)
                    bundle.putBoolean("status", status)
                    goToFragment(ResponseMenikahFragment(), bundle)
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

    fun hitungTotalBiaya() {
        if(etTotalBiayaResepsi.text.isNotEmpty() && etMenikahDalam.text.isNotEmpty() && etAsumsiInflasi.text.isNotEmpty()) {
            var final = etTotalBiayaResepsi.text.replace("[Rp,.]".toRegex(), "").toInt()
            val lama = etMenikahDalam.text.replace("[Rp,.]".toRegex(), "").toInt()
            val inflasi = etAsumsiInflasi.text.replace("[Rp,.]".toRegex(), "").toInt()
            for (i in 1..lama) {
                final += final * inflasi / 100
            }
            etTotalUangYangDiperlukan.setText(formatter.format(final))
        }
    }
}