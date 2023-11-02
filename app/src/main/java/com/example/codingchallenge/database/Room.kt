package com.example.codingchallenge.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Query("select * from databaserepo")
    fun getRepos(): Flow<List<DatabaseRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepo(vararg repos: DatabaseRepo)
}

@Database(entities = [DatabaseRepo::class], version = 1)
abstract class ReposDatabase : RoomDatabase() {
    abstract val repoDao: RepoDao
}

private lateinit var INSTANCE: ReposDatabase

fun getDatabase(context: Context): ReposDatabase {
    synchronized(ReposDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ReposDatabase::class.java,
                "repositories").build()
        }
    }
    return INSTANCE
}
