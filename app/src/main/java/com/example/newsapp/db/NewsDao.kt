package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.data.Favorite

@Dao
interface NewsDao {


    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Favorite)

    @Delete
    suspend fun delete(article: Favorite)

    @Query("SELECT * FROM newsInformation")
    fun getSavedNews()  :LiveData<List<Favorite>>


}