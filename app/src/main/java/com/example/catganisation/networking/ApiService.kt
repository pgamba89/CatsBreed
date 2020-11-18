package com.example.catganisation.networking

import com.example.catganisation.model.Breed
import com.example.catganisation.model.CatModel
import com.example.catganisation.model.SignInBody
import com.example.catganisation.model.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

var BASE_URL: String = "https://api.thecatapi.com/v1/"
var API_KEY : String ="c8016ad7-8edc-4dcf-98c9-93023f90f6d9"

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
    ): Call<ResponseBody>

    @GET("images/search")
    suspend fun getCatAsync(@Query("breed_id") type: String): List<CatModel>

    @Headers("x-api-key:c8016ad7-8edc-4dcf-98c9-93023f90f6d9")
    @GET("breeds")
    suspend fun getCatBreeds(): List<Breed>
}

object CatsApi {
    val retrofitServiceLogin: ApiService by lazy { retrofitFakeLogin.create(ApiService::class.java) }
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}