package com.example.catganisation.catDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catganisation.model.Breed

class CatDetailModelView(breed: Breed, application: Application) : AndroidViewModel(application) {

    private val _breedSelected = MutableLiveData<Breed>()

    val breedSelected: LiveData<Breed>
        get() = _breedSelected

    init {
        _breedSelected.value = breed
    }
}