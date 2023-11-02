package com.example.codingchallenge.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codingchallenge.utils.ParcelableRepo

class DetailViewModel(parcelableRepo: ParcelableRepo, application: Application) : AndroidViewModel(application) {
    private val _selectedProperty = MutableLiveData<ParcelableRepo>()

    val selectedProperty: LiveData<ParcelableRepo>
        get() = _selectedProperty

    init {
        _selectedProperty.value = parcelableRepo
    }
}