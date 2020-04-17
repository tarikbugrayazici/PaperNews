package com.example.papernews.ui.navigationFragments.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.papernews.R
import com.example.papernews.data.entity.Sources
import com.example.papernews.db.NewsRepository
import com.example.papernews.ui.navigationFragments.adapter.FavoriteAdapter
import com.example.papernews.ui.navigationFragments.adapter.SourceAdapter
import kotlinx.android.synthetic.main.recycler_view.*

class FavoriteFragment() : Fragment() {

    private var layoutManager: LinearLayoutManager? = null
    private var adapter: FavoriteAdapter? = null
    private var liste = ArrayList<Sources>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository = NewsRepository(activity!!)

        liste = newsRepository.getFromDbAllFavorite()!!
        setRecyclerView(liste)
    }

    private fun setRecyclerView(list: ArrayList<Sources>?) {
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = FavoriteAdapter(context!!, list!!) { position -> whenClickOnItem(position) }
        recyclerView.adapter = adapter
    }

    private fun whenClickOnItem(position: Int) {
        val news = liste!![position]
        val newsRepository = NewsRepository(activity!!)
        newsRepository.updateList(news)
        setRecyclerView(newsRepository.getFromDbAllFavorite())
    }
}