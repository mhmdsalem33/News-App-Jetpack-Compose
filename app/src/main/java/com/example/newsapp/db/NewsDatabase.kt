package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.Favorite

@Database(entities = [Favorite::class]  , version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase  : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}