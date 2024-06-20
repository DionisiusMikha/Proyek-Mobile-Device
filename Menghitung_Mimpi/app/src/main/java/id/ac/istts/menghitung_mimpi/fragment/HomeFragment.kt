package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.ac.istts.menghitung_mimpi.R
import java.util.Calendar

class HomeFragment : Fragment() {

    lateinit var timeTV: TextView
    lateinit var btn_calcInves: Button
    lateinit var btn_SpendAnalysis: Button
    lateinit var btn_DanaDarurat: Button
    lateinit var btn_Menikah: Button
    lateinit var btn_TanyaPenggu: Button
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timeTV = view.findViewById(R.id.textView28)
        btn_DanaDarurat = view.findViewById(R.id.btn_DanaDarurat)
        btn_TanyaPenggu = view.findViewById(R.id.btn_TanyaPenggu)
        btn_Menikah = view.findViewById(R.id.btn_Menikah)
        btn_SpendAnalysis = view.findViewById(R.id.btn_SpendAnalysis)
        btn_calcInves = view.findViewById(R.id.btn_calcInvest)
        val c = Calendar.getInstance()
        val timeOfDay = c.get(Calendar.HOUR_OF_DAY)

        if (timeOfDay >= 0 && timeOfDay < 12) {
            timeTV.text = "Good Morning"
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            timeTV.text = "Good Afternoon"
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            timeTV.text = "Good Evening"
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            timeTV.text = "Good Night"
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
