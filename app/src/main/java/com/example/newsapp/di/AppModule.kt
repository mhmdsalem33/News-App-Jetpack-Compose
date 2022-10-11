package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.db.NewsDatabase
import com.example.newsapp.network.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi =
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app : Application) : NewsDatabase =
        Room.databaseBuilder(app , NewsDatabase::class.java  , "news.db").build()

}