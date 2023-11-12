package com.beside.hackathon.presentation.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beside.hackathon.data.model.home.TotalRankScore
import com.beside.hackathon.databinding.ItemTotalRankingBinding

class TotalRankingAdapter(
    private val totalRankScores: List<TotalRankScore>,
    private val itemClickListener: ((TotalRankScore) -> Unit)?) : RecyclerView.Adapter<TotalRankingAdapter.RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = ItemTotalRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val rankScore = totalRankScores[position]
        holder.bind(rankScore)
    }

    override fun getItemCount(): Int = totalRankScores.size

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemTotalRankingBinding = ItemTotalRankingBinding.bind(itemView)

        fun bind(ranking: TotalRankScore) {
            itemView.setOnClickListener {
                itemClickListener?.invoke(ranking)
            }

            binding.rankingNumber.text = ranking.rank.toString()
            binding.userName.text = ranking.nickname
            binding.userUniv.text = ranking.schoolName
            binding.rankingPoint.text = ranking.score.toString()
        }
    }
}