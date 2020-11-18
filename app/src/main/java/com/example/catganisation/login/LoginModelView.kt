package com.example.catganisation.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catganisation.model.SignInBody
import com.example.catganisation.model.User
import com.example.catganisation.repository.CatRepository

class LoginModelView @ViewModelInject constructor(private val repository: CatRepository) :ViewModel() {

    private val _loginResponse = MutableLiveData<User>()

    val loginResponse: LiveData<User>
        get() = _loginResponse

     suspend fun signIn(signInBody: SignInBody) {
        _loginResponse.postValue(repository.signIn(signInBody))
    }
}