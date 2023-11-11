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
import java.util.Calendar

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

//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

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

        // 현재 날짜와 요일 확인
        setupViewsBasedOnDayOfWeek()
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
            binding.rankingTitle.text = when (page) {
                0 -> "전체 TOP 랭킹 -" // TotalRankingFragment에 대한 텍스트
                1 -> "학교별 TOP 랭킹 -" // UnivRankingFragment에 대한 텍스트
                else -> "" // 기본값 또는 다른 페이지에 대한 텍스트
            }
        })
    }

    private fun setupViewsBasedOnDayOfWeek() {
        val today = Calendar.getInstance()
        when (today.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> {
                // 일요일일 경우
                binding.todayCardnews.visibility = View.GONE
                binding.todayQuiz.visibility = View.VISIBLE
                binding.todayCardnewsView.visibility = View.GONE
                binding.todayQuizView.visibility = View.VISIBLE
                binding.cardnewsBtn.visibility = View.GONE
                binding.quizBtn.visibility = View.VISIBLE
            }
            else -> {
                // 월요일부터 토요일일 경우
                binding.todayCardnews.visibility = View.VISIBLE
                binding.todayQuiz.visibility = View.GONE
                binding.todayCardnewsView.visibility = View.VISIBLE
                binding.todayQuizView.visibility = View.GONE
                binding.cardnewsBtn.visibility = View.VISIBLE
                binding.quizBtn.visibility = View.GONE
            }
        }
    }
}
