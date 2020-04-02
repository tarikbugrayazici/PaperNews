package com.example.papernews.ui.navigationFragments.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.papernews.R
import com.example.papernews.data.entity.Sources
import com.example.papernews.db.NewsRepository
import com.example.papernews.ui.navigationFragments.adapter.SourceAdapter
import kotlinx.android.synthetic.main.recycler_view.*

class SourceFragment : androidx.fragment.app.Fragment() {
    private var layoutManager: androidx.recyclerview.widget.LinearLayoutManager? = null
    private var adapter: SourceAdapter? = null
    private var isThereInternet: Boolean? = true

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
            setRecyclerView(list, isThereInternet!!)
        }
    }

    private fun setRecyclerView(list: ArrayList<Sources>?, isThereInternet: Boolean) {
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = SourceAdapter(context!!, list!!, isThereInternet)
        recyclerView.adapter = adapter
    }
}