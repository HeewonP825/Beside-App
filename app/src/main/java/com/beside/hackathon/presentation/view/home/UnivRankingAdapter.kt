package com.beside.hackathon.presentation.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beside.hackathon.data.model.home.SchoolRankingScore
import com.beside.hackathon.databinding.ItemUnivRankingBinding

class UnivRankingAdapter(
    private val schoolRankingScores: List<SchoolRankingScore>,
    private val itemClickListener: ((SchoolRankingScore) -> Unit)?) : RecyclerView.Adapter<UnivRankingAdapter.RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = ItemUnivRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val schoolRankingScore = schoolRankingScores[position]
        holder.bind(schoolRankingScore)
    }

    override fun getItemCount(): Int = schoolRankingScores.size

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemUnivRankingBinding = ItemUnivRankingBinding.bind(itemView)

        fun bind(ranking: SchoolRankingScore) {
            itemView.setOnClickListener {
                itemClickListener?.invoke(ranking)
            }

            binding.rankingNumber.text = ranking.rank.toString()
            binding.userUniv.text = ranking.schoolName
            binding.rankingPoint.text = ranking.score.toString()
        }
    }
}