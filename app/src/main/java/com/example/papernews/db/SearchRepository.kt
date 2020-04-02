package com.example.papernews.db

import com.example.papernews.core.util.Constants
import com.example.papernews.data.entity.Articles
import com.example.papernews.data.entity.TopHeadlines
import com.example.papernews.data.service.API
import com.example.papernews.data.service.RetrofitService
import org.jetbrains.anko.doAsyncResult

class SearchRepository(private val searchText: String) {

    fun getSearchedNews(block: (ArrayList<Articles>?) -> Unit) {
        getFromService(block)
    }

    fun getFromService(block: (ArrayList<Articles>?) -> Unit) {
        return doAsyncResult {
            var retrofit: API = RetrofitService.getRetrofit().create(API::class.java)
            val call = retrofit.getSearched(searchText, Constants.API_KEY)
            call.enqueue(object : retrofit2.Callback<TopHeadlines> {
                override fun onResponse(
                    call: retrofit2.Call<TopHeadlines>,
                    response: retrofit2.Response<TopHeadlines>
                ) {
                    block(response.body()!!.articles)
                }

                override fun onFailure(
                    call: retrofit2.Call<TopHeadlines>,
                    t: kotlin.Throwable
                ) {
                }
            })
        }.get()
    }
}


