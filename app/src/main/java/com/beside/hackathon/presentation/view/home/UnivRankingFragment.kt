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
import com.beside.hackathon.R
import com.beside.hackathon.databinding.FragmentTotalRankingBinding
import com.beside.hackathon.databinding.FragmentUnivRankingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnivRankingFragment : Fragment() {
    private var _binding: FragmentUnivRankingBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private lateinit var univRankingAdapter : UnivRankingAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()

        _binding = FragmentUnivRankingBinding.inflate(inflater, container, false)


        recyclerView = binding.univRankingRv // RecyclerView ID에 맞게 변경
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.dataForUnivRankingFragment.observe(viewLifecycleOwner, Observer { data ->
            univRankingAdapter = UnivRankingAdapter(data) { item ->
                // 아이템 클릭 시 수행할 작업
            }
            recyclerView.adapter = univRankingAdapter
        })

        val root: View = binding.root

        return root
    }
}