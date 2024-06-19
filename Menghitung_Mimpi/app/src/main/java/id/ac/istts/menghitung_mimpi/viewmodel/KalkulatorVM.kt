package id.ac.istts.menghitung_mimpi.viewmodel

import android.icu.text.DecimalFormat
import androidx.lifecycle.ViewModel

class KalkulatorVM: ViewModel() {
    fun calculate(target: Int, waktu: Int, uangSkrg: Int, type: Int, range: Int, invest: Int, presentase: Int, onSuccess: (Int, Int, Int, Int, Int, Int, Boolean) -> Unit, onFailed: (String) -> Unit) {
        if(target < 0 || waktu < 0 || uangSkrg < 0 || invest < 0 || presentase < 0){
            onFailed("Invalid Input")
            return
        } else if(type < 0){
            onFailed("Silahkan pilih setiap Tahun/Bulan")
            return
        } else if(range < 0){
            onFailed("Silahkan pilih awal atau akhir Tahun/Bulan")
            return
        }

        var totalUang: Float = uangSkrg.toFloat()
        for (i in 1..waktu){
            if(type == 0){
                if (range == 0) {
                    totalUang += invest
                    totalUang += totalUang * presentase / 100
                } else {
                    totalUang += totalUang * presentase / 100
                    totalUang += invest
                }
            } else{
                if (range == 0) {
                    totalUang += invest * 12
                    totalUang += totalUang * presentase / 100
                } else {
                    totalUang += invest * 11
                    totalUang += totalUang * presentase / 100
                    totalUang += invest
                }
            }
        }

        val final: Int = totalUang.toInt()
        if(final < target){
            onSuccess(target, uangSkrg, invest, presentase, waktu, final, false)
        } else{
            onSuccess(target, uangSkrg, invest, presentase, waktu, final, true)
        }
    }
}
