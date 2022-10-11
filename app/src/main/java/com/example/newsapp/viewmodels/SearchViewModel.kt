package com.example.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Repositroy.SearchRepository
import com.example.newsapp.Utils.Resource
import com.example.newsapp.data.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepo :SearchRepository) :ViewModel() {


    private var _getNewsByCategoryData = MutableLiveData<News>()
    var getNewsByCategoryData :LiveData<News> = _getNewsByCategoryData


    fun getNewsByCategory(country :String , category :String , api :String)
    {
        viewModelScope.launch {
            try {
                val response =   searchRepo.getNewsByCategory(country , category , api)
                if (response.isSuccessful)
                {
                    response.body()?.let {
                        _getNewsByCategoryData.value = it
                    }
                }else
                {
                    Log.d("testApp" , response.message().toString())
                }
            }catch (t:Throwable)
            {
                Log.d("testApp" , t.message.toString())
            }
        }
    }



    private var _getSearchData = MutableLiveData<News>()
    var getSearchData : LiveData<News> = _getSearchData

    fun getSearchNews(searchQuery :String) {
        viewModelScope.launch {
            try {
                val response = searchRepo.searchForNews(searchQuery)
                if (response.isSuccessful)
                {
                    response.body()?.let {
                        _getSearchData.postValue(it)
                    }
                }
                else
                {
                    Log.d("testApp" , response.code().toString())
                    Log.d("testApp" , response.message().toString())
                }
            }catch (t :Throwable)
            {
                Log.d("testApp" , t.message.toString())
            }
        }
    }


}