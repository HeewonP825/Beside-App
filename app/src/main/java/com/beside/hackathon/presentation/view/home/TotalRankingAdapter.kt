package com.beside.hackathon.presentation.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beside.hackathon.data.model.home.RankScore
import com.beside.hackathon.databinding.ItemTotalRankingBinding

class TotalRankingAdapter(
    private val rankScores: List<RankScore>,
    private val itemClickListener: ((RankScore) -> Unit)?) : RecyclerView.Adapter<TotalRankingAdapter.RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = ItemTotalRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val rankScore = rankScores[position]
        holder.bind(rankScore)
    }

    override fun getItemCount(): Int = rankScores.size

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemTotalRankingBinding = ItemTotalRankingBinding.bind(itemView)

        fun bind(ranking: RankScore) {
            itemView.setOnClickListener {
                itemClickListener?.invoke(ranking)
            }

            binding.rankingNumber.text = ranking.rank.toString()
            binding.userName.text = ranking.name
            binding.userUniv.text = ranking.schoolName
            binding.rankingPoint.text = ranking.score.toString()
        }
    }
}