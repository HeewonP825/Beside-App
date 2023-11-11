package com.beside.hackathon.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.beside.hackathon.R
import com.beside.hackathon.databinding.FragmentTotalRankingBinding
import com.beside.hackathon.databinding.FragmentUnivRankingBinding

class UnivRankingFragment : Fragment() {
    private var _binding: FragmentUnivRankingBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()

        _binding = FragmentUnivRankingBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }
}