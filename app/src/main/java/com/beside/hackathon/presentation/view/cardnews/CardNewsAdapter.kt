package com.beside.hackathon.presentation.view.cardnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.beside.hackathon.R

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
        // 이미지 로딩 라이브러리 (예: Glide 또는 Picasso)를 사용하여 이미지 로드
        //Glide.with(holder.imageView.context).load(url).into(holder.imageView)
    }

    override fun getItemCount() = contentUrls.size
}