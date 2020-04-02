package com.example.papernews.ui.navigationFragments.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.papernews.R
import com.example.papernews.core.extensions.hideKeyboard
import com.example.papernews.data.entity.Articles
import com.example.papernews.db.SearchRepository
import com.example.papernews.ui.navigationFragments.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : androidx.fragment.app.Fragment() {
    private var adapter: SearchAdapter? = null
    private var layoutManager: androidx.recyclerview.widget.LinearLayoutManager? = null
    private var searchEditText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText.addTextChangedListener(object : MyTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.length > 3) s?.let {
                    searchEditText = s.toString()
                    val searchRepository = SearchRepository(searchEditText!!)
                    searchRepository.getSearchedNews { list -> setRecyclerView(list) }
                }
            }
        })
        view.hideKeyboard(view)
    }

    private fun setRecyclerView(list: ArrayList<Articles>?) {
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        searchRecyclerView.layoutManager = layoutManager
        adapter = SearchAdapter(context!!, list!!)
        searchRecyclerView.adapter = adapter
    }
}