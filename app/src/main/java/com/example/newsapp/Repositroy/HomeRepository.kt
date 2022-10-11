package com.example.newsapp.Repositroy

import android.util.Log
import com.example.newsapp.data.News
import com.example.newsapp.network.NewsApi
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val newsApi: NewsApi) {



   suspend fun getNewsCountry() = newsApi.getCountryNew("sa" ,"business" ,"1f46b043bcfc4d33994d728cf67a6318")




}