package id.ac.istts.menghitung_mimpi.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import id.ac.istts.menghitung_mimpi.R
import java.util.Calendar

class HomeFragment : Fragment() {

    lateinit var timeTV: TextView

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
    }
}
