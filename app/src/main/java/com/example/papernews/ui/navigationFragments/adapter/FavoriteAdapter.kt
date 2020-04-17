package com.example.papernews.ui.navigationFragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.papernews.R
import com.example.papernews.data.entity.Sources
import com.example.papernews.ui.navigationFragments.view.FavoriteFragment

class FavoriteAdapter(
    private val context: Context,
    private val list: ArrayList<Sources>,
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapterHolder {
        return FavoriteAdapterHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.fragment_source, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteAdapterHolder, position: Int) {
        val news = list[position]
        holder.apply {
            name.text = news.name
            descripton.text = news.description
            category.text = news.category
        }
        holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp)
        holder.favoriteButton.setOnClickListener {
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

    inner class FavoriteAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val category = itemView.findViewById<TextView>(R.id.category)
        val descripton = itemView.findViewById<TextView>(R.id.descripton)
        val favoriteButton = itemView.findViewById<ImageView>(R.id.favoriteButton)
    }
}