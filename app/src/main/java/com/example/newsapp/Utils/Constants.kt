package com.example.newsapp.Utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class Constants {

 fun okHttp(): OkHttpClient {
  val logging = HttpLoggingInterceptor()
  logging.setLevel(HttpLoggingInterceptor.Level.BODY)
  return OkHttpClient.Builder()
   .addInterceptor(logging)
   .build()
 }

}