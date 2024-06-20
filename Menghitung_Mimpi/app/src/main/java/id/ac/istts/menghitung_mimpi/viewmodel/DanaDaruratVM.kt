package id.ac.istts.menghitung_mimpi.viewmodel

import androidx.lifecycle.ViewModel

class DanaDaruratVM: ViewModel() {
    fun calculate(pengeluaranWajib: Int, statusMenikah: Int, tanggungan: Int, lama: Int, danaSkrg: Int, invest: Int, presentase: Int, onSuccess: (Int, Int, Int, Int, Int, Int, Boolean) -> Unit, onFailed: (String) -> Unit) {
        if(pengeluaranWajib < 0 || tanggungan < 1 || lama < 0 || danaSkrg < 0 || presentase < 0 || invest < 0){
            onFailed("Invalid Input")
            return
        } else if(statusMenikah < 0){
            onFailed("Pilih status menikah")
            return
        }

        var total: Int = 0
        var bunga: Float = presentase / 12f
        var danaDarurat: Int = 0

        for (i in 1..tanggungan){
            if(i == 1){
                danaDarurat += pengeluaranWajib * 9
            } else{
                danaDarurat += pengeluaranWajib * 3
            }
        }

        if(statusMenikah == 1 && tanggungan == 1) danaDarurat += pengeluaranWajib * 3

        bunga = String.format("%.2f", bunga).toFloat()

        total = danaSkrg
        for(i in 1..lama){
            total += invest
            total += (total * bunga / 100).toInt()
        }

        if(total < danaDarurat){
            onSuccess(danaDarurat, danaSkrg, invest, presentase, lama, total, false)
        } else{
            onSuccess(danaDarurat, danaSkrg, invest, presentase, lama, total, true)
        }

    }
}