package com.example.catganisation.networking

import com.example.catganisation.model.CatModel
import com.example.catganisation.model.SignInBody
import com.example.catganisation.model.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

var BASE_URL: String = "https://api.thecatapi.com/v1/"

private val retrofitFakeLogin = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(MockInterceptor()).build())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @POST("login")
    suspend fun signInAsync(@Body signInBody: SignInBody): User

    @Headers("Content-Type:application/json")
    @POST("users")
    fun registerUser(
        @Body info: User
    ): retrofit2.Call<ResponseBody>

    @GET("images/search")
    suspend fun getCatAsync(): List<CatModel>
}

object CatsApi {
    val retrofitServiceLogin: ApiService by lazy { retrofitFakeLogin.create(ApiService::class.java) }
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java)}
}