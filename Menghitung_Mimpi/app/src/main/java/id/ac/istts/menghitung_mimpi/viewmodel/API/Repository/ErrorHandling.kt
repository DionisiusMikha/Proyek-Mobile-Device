package id.ac.istts.menghitung_mimpi.viewmodel.API.Repository

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

@JsonClass(generateAdapter = true)
data class ErrorResponse<T>(
    @Json(name = "message") val message: String
)

class ErrorHandlingInterceptor(private val moshi: Moshi) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            val errorBody = response.body?.string()
            val adapter = moshi.adapter(ErrorResponse::class.java)
            val apiError = adapter.fromJson(errorBody)
            throw ApiException(apiError?.message ?: "Unknown error")
        }

        return response
    }
}

class ApiException(message: String) : IOException(message)