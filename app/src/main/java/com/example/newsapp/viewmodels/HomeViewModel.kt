package com.example.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Repositroy.HomeRepository
import com.example.newsapp.Utils.Resource
import com.example.newsapp.data.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo : HomeRepository) : ViewModel() {


    private var _getNewsCountryData = MutableLiveData<News>()
    var getNewsCountryData  : LiveData<News> = _getNewsCountryData


    init {
        getNewByCountry()
    }

     private fun getNewByCountry()
    {
         viewModelScope.launch {
             try {
             val response = homeRepo.getNewsCountry()
             if (response.isSuccessful)
            {
                response.body()?.let {
                    _getNewsCountryData.value = it
                }
            }
             else
            {
                Log.d("testApp" , response.message().toString())
            }
         }catch (t:Throwable)
             {   // handle offline errors
                Log.d("testApp" , t.message.toString())
             }
     }
    }


}