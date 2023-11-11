package com.beside.hackathon.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.beside.hackathon.R
import com.beside.hackathon.databinding.FragmentHomeBinding
import com.beside.hackathon.databinding.FragmentTotalRankingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TotalRankingFragment : Fragment() {
    private var _binding: FragmentTotalRankingBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private lateinit var totalRankingAdapter: TotalRankingAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()

        _binding = FragmentTotalRankingBinding.inflate(inflater, container, false)

        recyclerView = binding.totalRankingRv // RecyclerView ID에 맞게 변경
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.dataForTotalRankingFragment.observe(viewLifecycleOwner, Observer { data ->
            totalRankingAdapter = TotalRankingAdapter(data) { item ->
                // 아이템 클릭 시 수행할 작업
            }
            recyclerView.adapter = totalRankingAdapter
        })


        val root: View = binding.root

        return root
    }
}