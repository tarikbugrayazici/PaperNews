package com.example.papernews.ui.topHeadlinesActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.papernews.R
import com.example.papernews.data.entity.Articles
import kotlinx.android.synthetic.main.activity_top_headlines.view.*

class TopHeadlinesAdapter(
    private val context: Context,
    private val list: ArrayList<Articles>
) : androidx.recyclerview.widget.RecyclerView.Adapter<TopHeadlinesAdapter.TopHeadlinesHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopHeadlinesHolder {
        return TopHeadlinesHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.activity_top_headlines, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TopHeadlinesHolder, position: Int) {
        val articles = list[position]
        holder.itemView.author.text = articles.author
        holder.itemView.title.text = articles.title
        holder.itemView.descripton.text = articles.description
        holder.itemView.content.text = articles.content

    }

    inner class TopHeadlinesHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val author = itemView.findViewById<TextView>(R.id.author)
        val title = itemView.findViewById<TextView>(R.id.title)
        val descripton = itemView.findViewById<TextView>(R.id.descripton)
        val content = itemView.findViewById<TextView>(R.id.content)


    }
}

