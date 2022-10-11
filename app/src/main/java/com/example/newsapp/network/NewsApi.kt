package com.example.newsapp.network

import com.example.newsapp.data.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("top-headlines")
    suspend fun getCountryNew(
     @Query("country")    country      :String,
     @Query("category")   categoryName :String,
     @Query("apiKey")     Api          :String
    ) : Response<News>


    @GET("top-headlines")
    suspend fun getNewsByCategory(
        @Query("us")         country      : String,
        @Query("category")   categoryName : String,
        @Query("apiKey")     apiKey       : String
    ):Response<News>

    @GET("everything")
    suspend fun searchInNews(
        @Query("q")        query   : String,
        @Query("page")     page    : Int,
        @Query("apiKey")   apiKey  : String
    ):Response<News>


}