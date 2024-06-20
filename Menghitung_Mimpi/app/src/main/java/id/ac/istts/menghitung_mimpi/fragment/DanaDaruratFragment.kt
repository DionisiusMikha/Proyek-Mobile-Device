package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import id.ac.istts.menghitung_mimpi.R

class DanaDaruratFragment : Fragment() {
    lateinit var btnHitungDanaDarurat: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dana_darurat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnHitungDanaDarurat = view.findViewById(R.id.btnHitungDanaDarurat)

        btnHitungDanaDarurat.setOnClickListener {

        }
    }
}