package com.beside.hackathon.presentation.view.cardnews

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.beside.hackathon.R
import com.bumptech.glide.Glide

class CardNewsAdapter(private val contentUrls: List<String>) : RecyclerView.Adapter<CardNewsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cardnews, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = contentUrls[position]
        Glide.with(holder.itemView.context).load(url).into(holder.imageView)

        Log.d("CardNewsAdapter", "Loading image from URL: $url")
    }


    override fun getItemCount() = contentUrls.size
}