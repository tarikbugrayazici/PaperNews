package com.example.papernews.data.service

import com.example.papernews.data.entity.BaseEntity
import com.example.papernews.data.entity.TopHeadlines
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("sources")
    fun getNews(@Query("apiKey") apikey: String): Call<BaseEntity>

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("sources") id: String,
        @Query("apiKey") apikey: String
    ): Call<TopHeadlines>

    @GET("everything")
    fun getSearched(
        @Query("q") search: String,
        @Query("apiKey") apikey: String
    ): Call<TopHeadlines>
}