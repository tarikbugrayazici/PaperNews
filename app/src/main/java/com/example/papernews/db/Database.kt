package com.example.papernews.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.papernews.data.entity.Sources

@Database(entities = [Sources::class], version = 6, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

}