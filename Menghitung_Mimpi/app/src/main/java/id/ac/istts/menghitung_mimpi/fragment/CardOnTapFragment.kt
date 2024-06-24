package id.ac.istts.menghitung_mimpi.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.layout.TopupActivity
import id.ac.istts.menghitung_mimpi.viewmodel.Token

class CardOnTapFragment : Fragment() {

    lateinit var btnTopUp1: ImageButton
    lateinit var btnTopUp2: TextView
    lateinit var btnHistory1: ImageButton
    lateinit var btnHistory2: TextView
    lateinit var btnAddPocket1: ImageButton
    lateinit var btnAddPocket2: TextView
    lateinit var tvMainPocket: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_on_tap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnTopUp1 = view.findViewById(R.id.btnTopup1)
        btnTopUp2 = view.findViewById(R.id.btnTopup2)
        btnHistory1 = view.findViewById(R.id.btnHistory1)
        btnHistory2 = view.findViewById(R.id.btnHistory2)
        btnAddPocket1 = view.findViewById(R.id.btnAddPocket1)
        btnAddPocket2 = view.findViewById(R.id.btnAddPocket2)

        tvMainPocket = view.findViewById(R.id.tvMainPocket)

        val token = Token.getToken()
        tvMainPocket.text = "Rp. 100.000"
        btnTopUp1.setOnClickListener {
            val intent = Intent(activity, TopupActivity::class.java)
            startActivity(intent)
        }
        btnTopUp2.setOnClickListener {
            val intent = Intent(activity, TopupActivity::class.java)
            startActivity(intent)
        }
    }
}
