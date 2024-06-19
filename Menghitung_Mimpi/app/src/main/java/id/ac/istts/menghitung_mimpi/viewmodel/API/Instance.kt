package id.ac.istts.menghitung_mimpi.viewmodel.API

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.LoginService
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.RegisterService
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ErrorHandlingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://192.168.0.107:3000/api/"

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val okHttpClient =
            OkHttpClient.Builder().addInterceptor(ErrorHandlingInterceptor(moshi)).build()

    private val retrofit =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()

    val apiLogin: LoginService = retrofit.create(LoginService::class.java)
    val apiRegister: RegisterService = retrofit.create(RegisterService::class.java)
}
