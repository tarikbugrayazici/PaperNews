package com.example.papernews.ui.navigationFragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.papernews.R
import com.example.papernews.data.entity.Articles
import kotlinx.android.synthetic.main.fragment_source.view.*

class SearchAdapter(
    private val context: Context,
    private val list: ArrayList<Articles>
) : androidx.recyclerview.widget.RecyclerView.Adapter<SearchAdapter.SearchAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterHolder {
        return SearchAdapterHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.fragment_source, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchAdapterHolder, position: Int) {
        val news = list[position]
        holder.itemView.apply {
            name.text = news.title
            descripton.text = news.description
            category.text = news.content
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class SearchAdapterHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val descripton = itemView.findViewById<TextView>(R.id.descripton)
        val category = itemView.findViewById<TextView>(R.id.category)
    }
}
