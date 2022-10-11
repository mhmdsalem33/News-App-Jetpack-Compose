package com.example.newsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "newsInformation")
data class Favorite(
    val author     : String,
    val content    : String,
    @PrimaryKey
    val description: String,
    val publishedAt: String,
    val title      : String,
    val url        : String,
    val urlToImage : String
)