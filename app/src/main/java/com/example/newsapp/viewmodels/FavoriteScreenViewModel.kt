package com.example.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Repositroy.FavoriteNewsRepository
import com.example.newsapp.data.Article
import com.example.newsapp.data.Favorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(private val favoriteRepo : FavoriteNewsRepository) : ViewModel() {

    fun upsertArticle(article: Favorite) = viewModelScope.launch { favoriteRepo.upsertArticle(article) }

    fun deleteArticle(article: Favorite) = viewModelScope.launch { favoriteRepo.deleteArticle(article) }

    fun readSavedNews() = favoriteRepo.getSavedNews

}