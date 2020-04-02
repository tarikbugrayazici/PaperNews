package com.example.papernews.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.papernews.data.entity.Sources

@Dao
interface NewsDao {
    @Query("SELECT * FROM  sources")
    fun getAllNews(): List<Sources>

    @Insert
    fun insertAll(news: List<Sources>)

}