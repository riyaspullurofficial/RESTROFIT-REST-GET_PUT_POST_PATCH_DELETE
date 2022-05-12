package com.riyaspullur.learningrestapimethods

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        var BASE_URL="https://gorest.co.in/public/v1/"
        fun getRetroInstance():Retrofit{

            val logg=HttpLoggingInterceptor()
            logg.level=(HttpLoggingInterceptor.Level.BODY)

            val client=OkHttpClient.Builder()
            client.addInterceptor(logg)

          return  Retrofit.Builder()
                .baseUrl(BASE_URL)
              .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}