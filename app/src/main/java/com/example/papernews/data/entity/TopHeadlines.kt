package com.example.papernews.data.entity


data class TopHeadlines(
    var status: String? = null,
    var totalResults: Int? = 0,
    var articles: ArrayList<Articles>
)