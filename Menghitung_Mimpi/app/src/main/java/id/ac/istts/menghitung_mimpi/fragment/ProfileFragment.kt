package id.ac.istts.menghitung_mimpi.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.layout.LoginActivity
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.AuthFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.AuthRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.AuthVM
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    lateinit var layoutLogout: ConstraintLayout
    lateinit var tvFullnameUser: TextView
    lateinit var tvEmailUser: TextView

    private val vm: AuthVM by activityViewModels {
        AuthFactory(AuthRepo(RetrofitInstance.apiAuth))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutLogout = view.findViewById(R.id.layoutLogout)
        layoutLogout.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        tvFullnameUser = view.findViewById(R.id.tvFullnameUser)
        tvEmailUser = view.findViewById(R.id.tvEmailUser)

        coroutine.launch {
            vm.getName(Token.getToken()!!, onSuccess = { name, _, _, email ->
                activity?.runOnUiThread {
                    tvFullnameUser.text = name
                    tvEmailUser.text = email
                }
            }, onError = { error ->
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
