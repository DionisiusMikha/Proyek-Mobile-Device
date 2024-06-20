package id.ac.istts.menghitung_mimpi.viewmodel

import androidx.lifecycle.ViewModel

class NikahVM: ViewModel() {
    fun calculate(biaya: Int, waktu: Int, inflasi: Int, uangSkrg: Int, invest: Int, presentase: Int, onSuccess: (Int, Int, Int, Int, Int, Int, Boolean) -> Unit, onFailed: (String) -> Unit) {
        if(biaya < 0 || waktu < 0 || inflasi < 0 || uangSkrg < 0 || invest < 0 || presentase < 0){
            onFailed("Invalid Input")
            return
        }

        var biayaFinal: Int = biaya
        var totalFinal: Int = uangSkrg
        for (i in 1..waktu){
            biayaFinal += biayaFinal * inflasi / 100
            totalFinal += invest * 12
            totalFinal += totalFinal * presentase / 100
        }

        if(totalFinal < biayaFinal){
            onSuccess(biayaFinal, uangSkrg, invest, presentase, waktu, totalFinal, false)
        } else{
            onSuccess(biayaFinal, uangSkrg, invest, presentase, waktu, totalFinal, true)
        }
    }
}