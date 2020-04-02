package com.example.papernews.ui.navigationFragments.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.papernews.R
import com.example.papernews.core.extensions.ignoreNull
import com.example.papernews.data.entity.Sources
import com.example.papernews.ui.topHeadlinesActivity.view.TopHeadlinesActivity
import kotlinx.android.synthetic.main.fragment_source.view.*

class SourceAdapter(
    private val context: Context,
    private val list: ArrayList<Sources>,
    private var isThereInternet: Boolean
) : androidx.recyclerview.widget.RecyclerView.Adapter<SourceAdapter.SourceAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceAdapterHolder {
        return SourceAdapterHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.fragment_source, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SourceAdapterHolder, position: Int) {
        val news = list[position]
        holder.itemView.apply {
            name.text = news.name
            descripton.text = news.description
            category.text = news.category
        }
        if (isThereInternet)
            holder.itemView.setOnClickListener {
                val intent = Intent(context, TopHeadlinesActivity::class.java)
                intent.putExtra("id", news.sourceId.ignoreNull())
                context.startActivity(intent)
            }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class SourceAdapterHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val descripton = itemView.findViewById<TextView>(R.id.descripton)
        val category = itemView.findViewById<TextView>(R.id.category)
    }
}
