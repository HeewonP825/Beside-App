package com.beside.hackathon.presentation.view.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.beside.hackathon.databinding.FragmentSolveQuizBinding

class SolveQuizFragment : Fragment() {
    private var _binding: FragmentSolveQuizBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private lateinit var viewModel: QuizVIewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()

        _binding = FragmentSolveQuizBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }
}