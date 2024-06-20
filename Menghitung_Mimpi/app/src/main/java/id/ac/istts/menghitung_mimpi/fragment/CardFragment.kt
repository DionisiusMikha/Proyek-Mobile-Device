package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import id.ac.istts.menghitung_mimpi.R

class CardFragment : Fragment() {
    lateinit var ivCard: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivCard = view.findViewById(R.id.ivCard)

        ivCard.setOnClickListener{
            findNavController().navigate(R.id.action_global_cardOnTapFragment)
        }
    }
}