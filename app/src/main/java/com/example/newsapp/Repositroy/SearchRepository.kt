package com.example.newsapp.Repositroy

import com.example.newsapp.network.NewsApi
import javax.inject.Inject

class SearchRepository @Inject constructor(private val newsApi: NewsApi) {

    suspend fun getNewsByCategory(country :String , category :String , api :String) = newsApi.getNewsByCategory(country , category , api)

    suspend fun searchForNews (searchQuery : String) = newsApi.searchInNews(searchQuery , 1 , "fa9afc82f3c941da9bf12a2b1c9b6552")

}