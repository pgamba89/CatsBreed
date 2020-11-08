package com.example.catganisation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catganisation.model.SignInBody
import com.example.catganisation.model.User
import com.example.catganisation.repository.CatRepository

class LoginModelView(application: Application) : AndroidViewModel(application) {

    private val repository: CatRepository = CatRepository()

    private val _loginResponse = MutableLiveData<User>()

    val loginResponse: LiveData<User>
        get() = _loginResponse

     suspend fun signIn(signInBody: SignInBody) {
        _loginResponse.postValue(repository.signIn(signInBody))
    }
}