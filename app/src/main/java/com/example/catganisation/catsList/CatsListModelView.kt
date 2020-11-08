package com.example.catganisation.catsList

import android.app.Application
import androidx.lifecycle.*
import com.example.catganisation.model.CatModel
import com.example.catganisation.repository.CatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CatsListModelView (application: Application) : AndroidViewModel(application){

    private val repository: CatRepository = CatRepository()

    private val _catModel = MutableLiveData<List<CatModel>>()

    val catModel: LiveData<List<CatModel>>
        get() = _catModel

    init {
        getCat()
    }


    private fun getCat() = viewModelScope.launch(Dispatchers.IO)  {
            _catModel.postValue(repository.getCat())
    }

}