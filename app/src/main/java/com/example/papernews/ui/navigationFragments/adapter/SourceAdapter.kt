package com.example.papernews.ui.navigationFragments.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.papernews.R
import com.example.papernews.core.extensions.ignoreNull
import com.example.papernews.data.entity.Sources
import com.example.papernews.ui.topHeadlinesActivity.view.TopHeadlinesActivity
import kotlinx.android.synthetic.main.fragment_source.view.*

class SourceAdapter(
    private val context: Context,
    private val list: ArrayList<Sources>,
    private var listener: (Int) -> Unit

) : RecyclerView.Adapter<SourceAdapter.SourceAdapterHolder>() {

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

        if (news.isFavorite!!) {
            holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp)
        } else {
            holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp)
        }
        holder.favoriteButton.setOnClickListener {
            news.isFavorite = !news.isFavorite
            listener(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class SourceAdapterHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val category = itemView.findViewById<TextView>(R.id.category)
        val favoriteButton = itemView.findViewById<ImageView>(R.id.favoriteButton)
    }
}
