package com.example.catganisation.catDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.catganisation.model.Breed

class CatDetailModelView @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _breedSelected = MutableLiveData<Breed>()

    val breedSelected: LiveData<Breed>
        get() = _breedSelected

    init {
        val breed = savedStateHandle.get<Breed>("breedSelected")!!
        _breedSelected.value = breed
    }
}