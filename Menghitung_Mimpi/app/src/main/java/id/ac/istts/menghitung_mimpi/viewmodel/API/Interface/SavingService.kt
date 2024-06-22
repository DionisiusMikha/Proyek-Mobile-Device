package id.ac.istts.menghitung_mimpi.viewmodel.API.Interface

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingDanaDaruratRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingDanaDaruratResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingInvestRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingInvestResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingNikahRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingNikahResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SavingService {
    @POST("users/save-invest")
    suspend fun saveInvest(
        @Header("authorization") authorization: String?,
        @Body savingInvestRequest: SavingInvestRequest
    ): SavingInvestResponse<Any>

    @POST("users/save-darurat")
    suspend fun saveDanaDarurat(
        @Header("authorization") authorization: String?,
        @Body savingDanaDaruratRequest: SavingDanaDaruratRequest
    ): SavingDanaDaruratResponse<Any>

    @POST("users/save-nikah")
    suspend fun saveNikah(
        @Header("authorization") authorization: String?,
        @Body savingNikahRequest: SavingNikahRequest
    ): SavingNikahResponse<Any>
}