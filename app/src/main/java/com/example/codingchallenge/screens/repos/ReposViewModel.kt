package com.example.codingchallenge.screens.repos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.database.getDatabase
import com.example.codingchallenge.domain.Repo
import com.example.codingchallenge.repository.ReposRepository
import com.example.codingchallenge.utils.ParcelableRepo
import com.example.codingchallenge.utils.toParcelable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReposViewModel (application: Application) : AndroidViewModel(application) {

    var moreItems = true

    var page = 1

    private val _properties = MutableStateFlow<List<Repo>>(emptyList())

    val properties: StateFlow<List<Repo>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<ParcelableRepo>()

    val navigateToSelectedProperty: LiveData<ParcelableRepo>
        get() = _navigateToSelectedProperty

    private val database = getDatabase(application)
    private val reposRepository = ReposRepository(database)


    init {
        refreshRepos(page)
        getFreshRepos()
    }

    private fun getFreshRepos(){
        viewModelScope.launch {
            reposRepository.freshRepos.collect{
                _properties.value = it
            }
        }
    }

    fun refreshRepos(page : Int){
        viewModelScope.launch {
            reposRepository.refreshRepos(page)
            moreItems = reposRepository.moreItems
        }
    }

    fun displayPropertyDetails(repo: Repo) {
        _navigateToSelectedProperty.value = repo.toParcelable()
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun performSearch(query: String) {
        viewModelScope.launch {
            reposRepository.freshRepos.collect{
                _properties.value = it.filter { repo -> repo.name?.contains(query) ?: false }
            }
        }
    }

}