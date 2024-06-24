package id.ac.istts.menghitung_mimpi.viewmodel.API

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.AuthService
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.ForgotPasswordService
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.LoginService
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.RegisterService
import id.ac.istts.menghitung_mimpi.viewmodel.API.Interface.SavingService
import id.ac.istts.menghitung_mimpi.viewmodel.API.Repository.ErrorHandlingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://mdp.jensgelato.com/api/"

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
    val apiAuth: AuthService = retrofit.create(AuthService::class.java)
    val apiSave: SavingService = retrofit.create(SavingService::class.java)
    val apiPassword: ForgotPasswordService = retrofit.create(ForgotPasswordService::class.java)
}
