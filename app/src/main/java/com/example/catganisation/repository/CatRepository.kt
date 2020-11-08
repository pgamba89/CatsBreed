package com.example.catganisation.repository

import androidx.lifecycle.MutableLiveData
import com.example.catganisation.model.CatModel
import com.example.catganisation.model.SignInBody
import com.example.catganisation.model.User
import com.example.catganisation.networking.CatsApi
import kotlinx.coroutines.*

class CatRepository {

    suspend fun getCat(): List<CatModel> {
        return CatsApi.retrofitService.getCatAsync()
    }

    suspend fun signIn(signInBody: SignInBody): User {
        return CatsApi.retrofitServiceLogin.signInAsync(signInBody)
    }
}

