package com.example.papernews.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Sources(
    @PrimaryKey(autoGenerate = true)
    var dbid: Int,
    @ColumnInfo(name = "source_id")
    @SerializedName("id")
    var sourceId: String? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "url")
    var url: String? = null,
    @ColumnInfo(name = "category")
    var category: String? = null,
    @ColumnInfo(name = "language")
    var language: String? = null,
    @ColumnInfo(name = "country")
    var country: String? = null

)