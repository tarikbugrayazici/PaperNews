package com.example.papernews.db

import android.app.Activity
import androidx.room.Room
import com.example.papernews.core.util.Constants
import com.example.papernews.data.entity.BaseEntity
import com.example.papernews.data.entity.Sources
import com.example.papernews.data.service.API
import com.example.papernews.data.service.RetrofitService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import retrofit2.Call
import retrofit2.Response

class NewsRepository(private val activity: Activity) {

    private var db: DataBase? = null

    fun getNews(block: (ArrayList<Sources>?) -> Unit) {
        doAsync {
            db = Room.databaseBuilder(activity, DataBase::class.java, "news").build()
            getFromService(block)
        }
    }

    private fun getFromService(block: (ArrayList<Sources>?) -> Unit) {
        doAsync {
            val retrofit: API = RetrofitService.getRetrofit().create(API::class.java)
            val call = retrofit.getNews(Constants.API_KEY)
            call.enqueue(object : retrofit2.Callback<BaseEntity> {
                override fun onResponse(call: Call<BaseEntity>, response: Response<BaseEntity>) {
                    block(insertNews(response.body()!!.sources!!))
                }

                override fun onFailure(call: Call<BaseEntity>, t: Throwable) {
                    block(getFromDb())
                }
            })
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun getFromDb(): ArrayList<Sources>? {
        return doAsyncResult {
            db?.newsDao()?.getAllNews()?.let { ArrayList(it) }
        }.get()
    }

    private fun insertNews(list: ArrayList<Sources>): ArrayList<Sources>? {
        return doAsyncResult {
            list.let { db?.newsDao()?.insertAllSources(it) }
            getFromDb()
        }.get()
    }

    fun insertSource(source: Sources) {
        doAsync {
            db = Room.databaseBuilder(activity, DataBase::class.java, "news").build()
            db?.newsDao()?.insertSource(source)
        }
    }

    fun getFromDbAllFavorite(): ArrayList<Sources>? {
        return doAsyncResult {
            db = Room.databaseBuilder(activity, DataBase::class.java, "news").build()
            db?.newsDao()?.getFavoriteSources()?.let { ArrayList(it) }
        }.get()
    }

    fun updateList(source: Sources) {
        doAsync {
            db = Room.databaseBuilder(activity, DataBase::class.java, "news").build()
            db?.newsDao()?.deleteItem(source)
            
        }
    }

    fun getFromDatabaseAll(): ArrayList<Sources>? {
        return doAsyncResult {
            db = Room.databaseBuilder(activity, DataBase::class.java, "news").build()
            db?.newsDao()?.getAllNews()?.let { ArrayList(it) }
        }.get()
    }
}
