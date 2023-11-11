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
import androidx.viewpager2.widget.ViewPager2
import com.beside.hackathon.R
import com.beside.hackathon.databinding.FragmentHomeBinding
import com.beside.hackathon.presentation.viewmodel.quiz.QuizViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupViewPager()
        subscribeUi()

        val root: View = binding.root

        binding.quizBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_quizFragment)
        }

            return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)
        binding.viewPager2.adapter = viewPagerFragmentAdapter
    }

    private fun setupViewPager() {
        val viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)
        binding.viewPager2.adapter = viewPagerFragmentAdapter
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                homeViewModel.setCurrentPage(position)
            }
        })

        // ViewPager2와 DotsIndicator 연결
        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.viewPager2
        dotsIndicator.setViewPager2(viewPager)
    }

    private fun subscribeUi() {
        homeViewModel.currentPage.observe(viewLifecycleOwner, Observer { page ->
            // 현재 페이지에 따른 UI 업데이트 로직
        })
    }
}
