package com.beside.hackathon.presentation.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beside.hackathon.data.model.home.Ranking
import com.beside.hackathon.databinding.ItemTotalRankingBinding

class TotalRankingAdapter(
    private val rankings: List<Ranking>,
    private val itemClickListener: ((Ranking) -> Unit)?) : RecyclerView.Adapter<TotalRankingAdapter.RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = ItemTotalRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val ranking = rankings[position]
        holder.bind(ranking)
    }

    override fun getItemCount(): Int = rankings.size

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemTotalRankingBinding = ItemTotalRankingBinding.bind(itemView)

        fun bind(ranking: Ranking) {
            itemView.setOnClickListener {
                itemClickListener?.invoke(ranking)
            }

            binding.rankingNumber.text = ranking.ranking.toString()
            binding.userName.text = ranking.userNickname
            binding.userUniv.text = ranking.userUniv
            binding.rankingPoint.text = ranking.rankingPoint.toString()
        }
    }
}