package com.example.catganisation.catsList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catganisation.model.Breed
import com.example.catganisation.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatsListModelView @ViewModelInject constructor(private val repository: CatRepository) : ViewModel() {

    private val _catBreedsModel = MutableLiveData<List<Breed>>()

    val catBreeds: LiveData<List<Breed>>
        get() = _catBreedsModel

    private val _catBreedsFiltered = MutableLiveData<List<Breed>?>()

    val catBreedsFiltered: LiveData<List<Breed>?>
        get() = _catBreedsFiltered

    init {
        getBreeds()
    }

    fun getBreeds() = viewModelScope.launch(Dispatchers.IO) {
        val breedList = repository.getBreeds()
        for (i in breedList.indices) {
            val breedId = breedList[i].id
            viewModelScope.launch(Dispatchers.IO) {
                val cat = repository.getCat(breedId.toString())[0]
                breedList[i].urlImage = cat.url
                _catBreedsModel.postValue(breedList)
            }
        }
    }

    fun getBreedNoFilter() {
        _catBreedsModel.value = catBreeds.value
    }

    fun getBreedFilter(title: String) {
        val listBreed = catBreeds.value
        val listFilter = arrayListOf<Breed>()

        for (i in listBreed!!.indices) {
            if (listBreed[i].origin?.contains(title)!!) {
                listFilter.add(catBreeds.value!![i])
            }
        }
        _catBreedsFiltered.value = listFilter
    }

    fun clean() {
        _catBreedsFiltered.value = null
    }

}