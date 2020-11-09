package com.example.catganisation.catsList

import android.app.Application
import androidx.lifecycle.*
import com.example.catganisation.model.Breed
import com.example.catganisation.model.CatModel
import com.example.catganisation.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatsListModelView(application: Application) : AndroidViewModel(application) {

    private val repository: CatRepository = CatRepository()

    private val _catBreedsModel = MutableLiveData<List<Breed>>()

    val catBreeds: LiveData<List<Breed>>
        get() = _catBreedsModel

    init {
        getBreeds()
    }

    private fun getBreeds() = viewModelScope.launch(Dispatchers.IO) {
        val breedList = repository.getBreeds()
        for (i in breedList.indices) {
            val breedId = breedList[i].id
            viewModelScope.launch(Dispatchers.IO) {
                val cat = repository.getCat(breedId.toString())[0]
                breedList[i].urlImage = cat.url
            }
        }
        _catBreedsModel.postValue(breedList)
    }

}