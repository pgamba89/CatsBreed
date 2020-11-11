package com.example.catganisation.repository

import com.example.catganisation.model.Breed
import com.example.catganisation.model.CatModel
import com.example.catganisation.model.SignInBody
import com.example.catganisation.model.User
import com.example.catganisation.networking.CatsApi
import com.example.catganisation.utils.OpenForTesting

@OpenForTesting
 class CatRepository {

    suspend fun signIn(signInBody: SignInBody): User {
        return CatsApi.retrofitServiceLogin.signInAsync(signInBody)
    }

    suspend fun getCat(id: String): List<CatModel> {
        return CatsApi.retrofitService.getCatAsync(id)
    }

    suspend fun getBreeds(): List<Breed> {
        return CatsApi.retrofitService.getCatBreeds()
    }
}

