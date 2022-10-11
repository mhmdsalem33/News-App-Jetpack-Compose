package com.example.newsapp.Repositroy

import com.example.newsapp.data.Article
import com.example.newsapp.data.Favorite
import com.example.newsapp.db.NewsDatabase
import javax.inject.Inject

class FavoriteNewsRepository @Inject constructor(private val db : NewsDatabase) {

    suspend fun upsertArticle(article: Favorite) = db.newsDao().upsert(article)


    suspend fun deleteArticle(article: Favorite) = db.newsDao().delete(article)


    val getSavedNews = db.newsDao().getSavedNews()


}