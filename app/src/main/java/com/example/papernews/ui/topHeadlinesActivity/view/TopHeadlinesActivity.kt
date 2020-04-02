package com.example.papernews.ui.topHeadlinesActivity.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.papernews.R
import com.example.papernews.core.util.Constants
import com.example.papernews.data.entity.Articles
import com.example.papernews.data.entity.TopHeadlines
import com.example.papernews.data.service.API
import com.example.papernews.data.service.RetrofitService
import com.example.papernews.ui.topHeadlinesActivity.adapter.TopHeadlinesAdapter
import kotlinx.android.synthetic.main.recycler_view.*
import retrofit2.Call
import retrofit2.Response

class TopHeadlinesActivity : AppCompatActivity() {

    private var adapter: TopHeadlinesAdapter? = null
    private var layoutManager: androidx.recyclerview.widget.LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)
        val topHeadlinesId: String = intent.getStringExtra("id")
        fetchTopHeadlines(topHeadlinesId)
    }


    private fun fetchTopHeadlines(topHeadlinesId: String) {
        val retrofit = RetrofitService.getRetrofit().create(API::class.java)
        val call = retrofit.getTopHeadlines(topHeadlinesId, Constants.API_KEY)
        call.enqueue(object : retrofit2.Callback<TopHeadlines> {
            override fun onResponse(call: Call<TopHeadlines>, response: Response<TopHeadlines>) {
                setRecyclerView(response.body()!!.articles)
            }

            override fun onFailure(call: Call<TopHeadlines>, t: Throwable) {
                println("hata" + t)
            }
        })
    }

    private fun setRecyclerView(list: ArrayList<Articles>) {
        layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this@TopHeadlinesActivity)
        recyclerView.layoutManager = layoutManager
        adapter =
            TopHeadlinesAdapter(
                this@TopHeadlinesActivity,
                list
            )
        recyclerView.adapter = adapter
    }

}