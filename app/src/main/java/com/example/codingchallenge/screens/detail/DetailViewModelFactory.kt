package com.example.codingchallenge.screens.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codingchallenge.utils.ParcelableRepo

class DetailViewModelFactory(private val parcelableRepo: ParcelableRepo, private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(parcelableRepo, app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}

