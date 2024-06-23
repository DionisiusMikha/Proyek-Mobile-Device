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

    private var listInvest: List<Invest> = emptyList()
    private var listDanaDarurat: List<DanaDarurat> = emptyList()
    private var listNikah: List<Nikah> = emptyList()

    private var danaDaruratFilter = false
    private var menikahFilter = false
    private var investFilter = false
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

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        historyAdapter = HistoryAdapter(emptyList())
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

        fetchData()
    }

    private fun fetchData() {
        coroutineScope.launch {
            try {
                vm.getDanaDarurat(Token.getToken()!!, onSuccess = { list ->
                    listDanaDarurat = list
                }, onError = { error ->
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                    }
                })

                vm.getNikah(Token.getToken()!!, onSuccess = { list ->
                    listNikah = list
                }, onError = { error ->
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                    }
                })

                vm.getInvest(Token.getToken()!!, onSuccess = { list ->
                    listInvest = list
                }, onError = { error ->
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                    }
                })
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Error fetching data: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateAdapter() {
        val items: List<Any> = when {
            danaDaruratFilter -> listDanaDarurat
            investFilter -> listInvest
            menikahFilter -> listNikah
            else -> listInvest
        }

        requireActivity().runOnUiThread {
            historyAdapter = HistoryAdapter(items)
            recyclerView.adapter = historyAdapter
        }
    }
}
