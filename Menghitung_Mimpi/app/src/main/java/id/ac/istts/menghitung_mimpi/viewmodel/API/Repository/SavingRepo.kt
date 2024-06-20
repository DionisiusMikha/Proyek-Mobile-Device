package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingInvestRequest
import id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass.SavingInvestResponse
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
}