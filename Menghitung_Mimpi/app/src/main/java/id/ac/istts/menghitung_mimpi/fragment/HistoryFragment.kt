package id.ac.istts.menghitung_mimpi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Invest
import id.ac.istts.menghitung_mimpi.viewmodel.API.Factory.SavingFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.SavingRepo
import id.ac.istts.menghitung_mimpi.viewmodel.API.RetrofitInstance
import id.ac.istts.menghitung_mimpi.viewmodel.SavingVM
import id.ac.istts.menghitung_mimpi.viewmodel.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private val vm: SavingVM by activityViewModels {
        SavingFactory(SavingRepo(RetrofitInstance.apiSave))
    }
    private val coroutine = CoroutineScope(Dispatchers.IO)
    private var listHistory: List<Invest> = emptyList()

    private var filter: String = "invest"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coroutine.launch {
            vm.getInvest(Token.getToken()!!, onSuccess = { list ->
                listHistory = list
                println(listHistory[0])
            }, onError = { error ->
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}