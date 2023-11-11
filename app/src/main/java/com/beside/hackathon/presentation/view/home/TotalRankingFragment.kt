package com.beside.hackathon.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.beside.hackathon.R
import com.beside.hackathon.databinding.FragmentHomeBinding
import com.beside.hackathon.databinding.FragmentTotalRankingBinding

class TotalRankingFragment : Fragment() {
    private var _binding: FragmentTotalRankingBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()

        _binding = FragmentTotalRankingBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }
}