package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.fragment.Adapter.HistoryAdapter
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.DanaDarurat
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Invest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Nikah
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.SavingFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.SavingRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.SavingVM
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private lateinit var tvChooseKategori: TextView
    private lateinit var layoutPopUpFilter: ConstraintLayout
    private lateinit var btnMenikahFilter: Button
    private lateinit var btnDanaDaruratFilter: Button
    private lateinit var btnInvestFilter: Button

    private val vm: SavingVM by activityViewModels {
        SavingFactory(SavingRepo(RetrofitInstance.apiSave))
    }
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var listInvest: ArrayList<Invest> = arrayListOf()
    private var listDanaDarurat: ArrayList<DanaDarurat> = arrayListOf()
    private var listNikah: ArrayList<Nikah> = arrayListOf()

    private var danaDaruratFilter = false
    private var menikahFilter = false
    private var investFilter = true
    private var popup = false

    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvChooseKategori = view.findViewById(R.id.tvFilterKategori)
        layoutPopUpFilter = view.findViewById(R.id.layoutPopUpFilter)
        btnInvestFilter = view.findViewById(R.id.btnInvestFilter)
        btnDanaDaruratFilter = view.findViewById(R.id.btnDanaDaruratFilter)
        btnMenikahFilter = view.findViewById(R.id.btnMenikahFilter)
        recyclerView = view.findViewById(R.id.rvListHistory)

        fetchData()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fetchData()
        historyAdapter = HistoryAdapter(listInvest, onClick = { type, index ->
            if(type == "darurat"){
                val bundle = Bundle()
                val data = listDanaDarurat[index]
                bundle.putInt("danaDarurat", data.target)
                bundle.putInt("danaSkrg", data.uangSekarang)
                bundle.putInt("lama", data.waktu)
                bundle.putInt("invest", data.invest)
                bundle.putInt("presentase", data.presentase)
                bundle.putInt("total", data.finalAmount)
                bundle.putBoolean("status", data.status)
                bundle.putBoolean("simpan", false)
                goToFragment(ResponseDanaDaruratFragment(), bundle)
            } else if(type == "investasi"){
                val bundle = Bundle()
                val data = listInvest[index]
                bundle.putInt("target", data.target)
                bundle.putInt("lamawaktu", data.waktu)
                bundle.putInt("uang_sekarang", data.uangSekarang)
                bundle.putInt("invest", data.invest)
                bundle.putInt("presentase", data.presentase)
                bundle.putInt("final", data.finalAmount)
                bundle.putInt("statustype", data.type)
                bundle.putBoolean("status", data.status)
                bundle.putBoolean("simpan", false)
                goToFragment(ResponseKalkulatorInvestasiFragment(), bundle)
            } else if(type == "nikah"){
                val bundle = Bundle()
                val data = listNikah[index]
                bundle.putInt("biaya_final", data.biayaFinal)
                bundle.putInt("uang_sekarang", data.uangSekarang)
                bundle.putInt("invest", data.invest)
                bundle.putInt("presentase", data.presentase)
                bundle.putInt("waktu", data.waktu)
                bundle.putInt("total_final", data.totalFinal)
                bundle.putBoolean("status", data.status)
                bundle.putBoolean("simpan", false)
                goToFragment(ResponseMenikahFragment(), bundle)
            }
        })
        recyclerView.adapter = historyAdapter

        tvChooseKategori.setOnClickListener {
            if (!popup) {
                layoutPopUpFilter.visibility = View.VISIBLE
                popup = true
            } else {
                layoutPopUpFilter.visibility = View.GONE
                popup = false
            }
        }

        btnDanaDaruratFilter.setOnClickListener {
            danaDaruratFilter = true
            investFilter = false
            menikahFilter = false
            updateAdapter()
        }

        btnInvestFilter.setOnClickListener {
            investFilter = true
            danaDaruratFilter = false
            menikahFilter = false
            updateAdapter()
        }

        btnMenikahFilter.setOnClickListener {
            menikahFilter = true
            danaDaruratFilter = false
            investFilter = false
            updateAdapter()
        }
    }

    private fun fetchData() {
        coroutineScope.launch {
            try {
                if(investFilter == true){
                    vm.getDanaDarurat(Token.getToken()!!, onSuccess = { list ->
                        listDanaDarurat.clear()
                        listDanaDarurat.addAll(list)
                    }, onError = { error ->
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                        }
                    })
                } else if(danaDaruratFilter == true){
                    vm.getNikah(Token.getToken()!!, onSuccess = { list ->
                        listNikah.clear()
                        listNikah.addAll(list)
                    }, onError = { error ->
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                        }
                    })
                } else if(menikahFilter == true){
                    vm.getInvest(Token.getToken()!!, onSuccess = { list ->
                        listInvest.clear()
                        listInvest.addAll(list)
                    }, onError = { error ->
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                        }
                    })
                }
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Error fetching data: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateAdapter() {
        fetchData()
        val items: List<Any> = when {
            danaDaruratFilter -> listDanaDarurat
            investFilter -> listInvest
            menikahFilter -> listNikah
            else -> listInvest
        }

        requireActivity().runOnUiThread {
            historyAdapter = HistoryAdapter(items, onClick = { type, index ->
                if(type == "darurat"){
                    val bundle = Bundle()
                    val data = listDanaDarurat[index]
                    bundle.putInt("danaDarurat", data.target)
                    bundle.putInt("danaSkrg", data.uangSekarang)
                    bundle.putInt("lama", data.waktu)
                    bundle.putInt("invest", data.invest)
                    bundle.putInt("presentase", data.presentase)
                    bundle.putInt("total", data.finalAmount)
                    bundle.putBoolean("status", data.status)
                    bundle.putBoolean("simpan", false)
                    goToFragment(ResponseDanaDaruratFragment(), bundle)
                } else if(type == "investasi"){
                    val bundle = Bundle()
                    val data = listInvest[index]
                    bundle.putInt("target", data.target)
                    bundle.putInt("lamawaktu", data.waktu)
                    bundle.putInt("uang_sekarang", data.uangSekarang)
                    bundle.putInt("invest", data.invest)
                    bundle.putInt("presentase", data.presentase)
                    bundle.putInt("final", data.finalAmount)
                    bundle.putInt("statustype", data.type)
                    bundle.putBoolean("status", data.status)
                    bundle.putBoolean("simpan", false)
                    goToFragment(ResponseKalkulatorInvestasiFragment(), bundle)
                } else if(type == "nikah"){
                    val bundle = Bundle()
                    val data = listNikah[index]
                    bundle.putInt("biaya_final", data.biayaFinal)
                    bundle.putInt("uang_sekarang", data.uangSekarang)
                    bundle.putInt("invest", data.invest)
                    bundle.putInt("presentase", data.presentase)
                    bundle.putInt("waktu", data.waktu)
                    bundle.putInt("total_final", data.totalFinal)
                    bundle.putBoolean("status", data.status)
                    bundle.putBoolean("simpan", false)
                    goToFragment(ResponseMenikahFragment(), bundle)
                }
            })
            recyclerView.adapter = historyAdapter
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
