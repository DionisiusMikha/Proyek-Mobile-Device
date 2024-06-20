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
import id.ac.istts.menghitung_mimpi.viewmodel.DanaDaruratVM
import java.text.DecimalFormat
import java.text.NumberFormat

class ChatBotFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat_bot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etPengeluaranWajibTiapBulan = view.findViewById(R.id.etPengeluaranWajibTiapBulan)
        etJumlahTanggungan = view.findViewById(R.id.etJumlahTanggungan)
        etTargetBulanDanaDarurat = view.findViewById(R.id.etTargetBulanDanaDarurat)
        etJumlahDanaDaruratSaatIni = view.findViewById(R.id.etJumlahDanaDaruratSaatIni)
        etTargetInvestasiDanaDarurat = view.findViewById(R.id.etTargetInvestasiDanaDarurat)
        etProdukReturDanaDarurat = view.findViewById(R.id.etProdukReturDanaDarurat)
        btnSudahMenikah = view.findViewById(R.id.btnSudahMenikah)
        btnBelumMenikah = view.findViewById(R.id.btnBelumMenikah)
        btnHitungDanaDarurat = view.findViewById(R.id.btnHitungDanaDarurat)

        etPengeluaranWajibTiapBulan.addTextChangedListener(object : TextWatcher {
            private var current: String? = ""
            private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etPengeluaranWajibTiapBulan.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                    if (cleanString.isNotEmpty()) {
                        try {
                            val parsed = cleanString.toDouble()
                            val formatted = currencyFormatter.format(parsed)

                            current = formatted
                            etPengeluaranWajibTiapBulan.setText(formatted)
                            etPengeluaranWajibTiapBulan.setSelection(formatted.length)
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }
                    } else {
                        current = ""
                        etPengeluaranWajibTiapBulan.setText("")
                        etPengeluaranWajibTiapBulan.setSelection(0)
                    }

                    etPengeluaranWajibTiapBulan.addTextChangedListener(this)
                }
            }
        })
        etTargetBulanDanaDarurat.addTextChangedListener(object : TextWatcher {
            private var current: String? = ""
            private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etTargetBulanDanaDarurat.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                    if (cleanString.isNotEmpty()) {
                        try {
                            val parsed = cleanString.toDouble()
                            val formatted = currencyFormatter.format(parsed)

                            current = formatted
                            etTargetBulanDanaDarurat.setText(formatted)
                            etTargetBulanDanaDarurat.setSelection(formatted.length)
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }
                    } else {
                        current = ""
                        etTargetBulanDanaDarurat.setText("")
                        etTargetBulanDanaDarurat.setSelection(0)
                    }

                    etTargetBulanDanaDarurat.addTextChangedListener(this)
                }
            }
        })
        etJumlahDanaDaruratSaatIni.addTextChangedListener(object : TextWatcher {
            private var current: String? = ""
            private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etPengeluaranWajibTiapBulan.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                    if (cleanString.isNotEmpty()) {
                        try {
                            val parsed = cleanString.toDouble()
                            val formatted = currencyFormatter.format(parsed)

                            current = formatted
                            etJumlahDanaDaruratSaatIni.setText(formatted)
                            etJumlahDanaDaruratSaatIni.setSelection(formatted.length)
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }
                    } else {
                        current = ""
                        etJumlahDanaDaruratSaatIni.setText("")
                        etJumlahDanaDaruratSaatIni.setSelection(0)
                    }

                    etJumlahDanaDaruratSaatIni.addTextChangedListener(this)
                }
            }
        })
        etTargetInvestasiDanaDarurat.addTextChangedListener(object : TextWatcher {
            private var current: String? = ""
            private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etTargetInvestasiDanaDarurat.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                    if (cleanString.isNotEmpty()) {
                        try {
                            val parsed = cleanString.toDouble()
                            val formatted = currencyFormatter.format(parsed)

                            current = formatted
                            etTargetInvestasiDanaDarurat.setText(formatted)
                            etTargetInvestasiDanaDarurat.setSelection(formatted.length)
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }
                    } else {
                        current = ""
                        etTargetInvestasiDanaDarurat.setText("")
                        etTargetInvestasiDanaDarurat.setSelection(0)
                    }

                    etTargetInvestasiDanaDarurat.addTextChangedListener(this)
                }
            }
        })
        etProdukReturDanaDarurat.addTextChangedListener(object : TextWatcher {
            private var current: String? = ""
            private val currencyFormatter: NumberFormat = DecimalFormat("#,###")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etProdukReturDanaDarurat.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[Rp,.]".toRegex(), "")

                    if (cleanString.isNotEmpty()) {
                        try {
                            val parsed = cleanString.toDouble()
                            val formatted = currencyFormatter.format(parsed)

                            current = formatted
                            etProdukReturDanaDarurat.setText(formatted)
                            etProdukReturDanaDarurat.setSelection(formatted.length)
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                        }
                    } else {
                        current = ""
                        etProdukReturDanaDarurat.setText("")
                        etProdukReturDanaDarurat.setSelection(0)
                    }

                    etProdukReturDanaDarurat.addTextChangedListener(this)
                }
            }
        })

        btnSudahMenikah.setOnClickListener{ statusMenikah = 1 }
        btnBelumMenikah.setOnClickListener{ statusMenikah = 0 }
        btnHitungDanaDarurat.setOnClickListener{
            val pengeluaranWajib: Int = etPengeluaranWajibTiapBulan.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val statusMenikah: Int = statusMenikah
            val tanggungan: Int = etJumlahTanggungan.text.toString().toInt()
            val lama: Int = etTargetBulanDanaDarurat.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val danaSkrg: Int = etJumlahDanaDaruratSaatIni.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val invest: Int = etTargetInvestasiDanaDarurat.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            val presentase: Int = etProdukReturDanaDarurat.text.toString().replace("[Rp,.]".toRegex(), "").toInt()
            vm.calculate(pengeluaranWajib, statusMenikah, tanggungan, lama, danaSkrg, invest, presentase, onSuccess = { danaDarurat, danaSkrg, invest, presentase, lama, total, status ->
                activity?.runOnUiThread {
                    val bundle = Bundle()
                    bundle.putInt("danaDarurat", danaDarurat)
                    bundle.putInt("danaSkrg", danaSkrg)
                    bundle.putInt("lama", lama)
                    bundle.putInt("invest", invest)
                    bundle.putInt("presentase", presentase)
                    bundle.putInt("total", total)
                    bundle.putBoolean("status", status)
                    goToFragment(ResponseDanaDaruratFragment(), bundle)
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