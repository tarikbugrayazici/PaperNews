package com.example.papernews.db

import androidx.room.*
import com.example.papernews.data.entity.Sources

@Dao
interface NewsDao {
    @Query("SELECT * FROM  sources")
    fun getAllNews(): List<Sources>

    @Query("SELECT * FROM sources WHERE isFavorite = 1")
    fun getFavoriteSources(): List<Sources>

    @Query("SELECT * FROM sources WHERE isFavorite = 0")
    fun getUnFav(): List<Sources>

    @Insert
    fun insertAllSources(source: List<Sources>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSource(source: Sources)

    @Delete
    fun deleteItem(source: Sources)

}