package com.example.codingchallenge.repository

import android.util.Log
import com.example.codingchallenge.database.ReposDatabase
import com.example.codingchallenge.database.asDomainModel
import com.example.codingchallenge.domain.Repo
import com.example.codingchallenge.network.RepoApi
import com.example.codingchallenge.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ReposRepository(private val database: ReposDatabase) {

    val freshRepos: Flow<List<Repo>> =
        database.repoDao.getRepos().map {
            it.asDomainModel()
        }

    var moreItems = true

    suspend fun refreshRepos(page : Int) {
        withContext(Dispatchers.IO) {
            try {
                val repos = RepoApi.retrofitService.getReposPropertiesAsync(page).await()
                moreItems = repos.isNotEmpty()
                database.repoDao.insertRepo(*repos.asDatabaseModel())
            }
            catch (e: HttpException) {
                Log.e(this.toString(), "Access Forbidden, old data from database will be shown")
            } catch (e: IOException) {
                Log.e(this.toString(), "Network error, old data from database will be shown")
            }
        }
    }

}
