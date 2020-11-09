package com.example.catganisation.catDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catganisation.model.Breed

class CatDetailModelViewFactory(
    private val breed: Breed,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatDetailModelView::class.java)) {
                return CatDetailModelView(breed, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}