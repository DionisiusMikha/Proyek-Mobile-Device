package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.DanaDarurat
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Invest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.Nikah
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingDanaDaruratRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingDanaDaruratResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingInvestRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingInvestResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingNikahRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingNikahResponse
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.SavingService

class SavingRepo  (private val savingService: SavingService) {
    suspend fun saveInvest(token: String, target: Int, waktu: Int, uang_sekarang: Int, invest: Int, presentase: Int, final: Int, type: Int, status: Boolean): Result<SavingInvestResponse<Any>> {
        return try {
            val response = savingService.saveInvest("Bearer ${token}", SavingInvestRequest(target, waktu, uang_sekarang, invest, presentase, final, type, status))
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun saveDanaDarurat(token: String, dana_darurat: Int, dana_sekarang: Int, lama: Int, invest: Int, presentase: Int, total: Int, status: Boolean): Result<SavingDanaDaruratResponse<Any>> {
        return try {
            val response = savingService.saveDanaDarurat("Bearer ${token}", SavingDanaDaruratRequest(dana_darurat, dana_sekarang, lama, invest, presentase, total, status))
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun saveNikah(token: String, biaya_final: Int, uang_sekarang: Int, invest: Int, presentase: Int, waktu: Int, total_final: Int, status: Boolean): Result<SavingNikahResponse<Any>> {
        return try {
            val response = savingService.saveNikah("Bearer ${token}", SavingNikahRequest(biaya_final, uang_sekarang, invest, presentase, waktu, total_final, status))
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getInvest(token: String): Result<List<Invest>> {
        return try {
            val response = savingService.getInvest("Bearer ${token}")
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDanaDarurat(token: String): Result<List<DanaDarurat>> {
        return try {
            val response = savingService.getDanaDarurat("Bearer ${token}")
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getNikah(token: String): Result<List<Nikah>> {
        return try {
            val response = savingService.getNikah("Bearer ${token}")
            Result.success(response)
        } catch (e: ApiException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}