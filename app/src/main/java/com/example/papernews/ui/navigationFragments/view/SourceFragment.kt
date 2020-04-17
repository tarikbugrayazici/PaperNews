package com.example.papernews.ui.navigationFragments.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.papernews.R
import com.example.papernews.data.entity.Sources
import com.example.papernews.db.NewsRepository
import com.example.papernews.ui.navigationFragments.adapter.SourceAdapter
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.recycler_view.*

class SourceFragment : Fragment() {
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: SourceAdapter? = null
    private var isThereInternet: Boolean? = true
    private var liste = ArrayList<Sources>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val newsRepository = NewsRepository(activity!!)
        newsRepository.getNews { list ->
            setRecyclerView(list)
        }
    }

    private fun setRecyclerView(list: ArrayList<Sources>?) {
        liste?.addAll(list!!)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = SourceAdapter(context!!, list!!) { position -> whenClickOnItem(position) }
        recyclerView.adapter = adapter

    }

    private fun whenClickOnItem(position: Int) {
        val news = liste!![position]
        val newsRepository = NewsRepository((activity as Activity?)!!)
        if (news.isFavorite) {
            newsRepository.insertSource(news)
        } else {
            newsRepository.updateList(news)
        }
     //   newsRepository.getFromDatabaseAll()
    }
}
