package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.PocketItem
import retrofit2.Call
import retrofit2.http.GET

interface TopupService {
    @GET("topup") fun getTopupItems(): Call<List<PocketItem>>
    
}
