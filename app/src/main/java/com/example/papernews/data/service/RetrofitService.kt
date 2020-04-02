package com.example.papernews.data.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitService {
    companion object {
        private val BASE_URL = "https://newsapi.org/v2/"
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit!!

        }

    }
}

