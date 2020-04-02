package com.example.papernews.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Articles(
    @PrimaryKey(autoGenerate = true)
    var dbid: Int,
    var source: Source? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
)