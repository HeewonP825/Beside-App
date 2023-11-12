package com.beside.hackathon.presentation.view.cardnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.beside.hackathon.R
import com.beside.hackathon.data.model.cardnews.CardNewsUrls
import com.beside.hackathon.databinding.FragmentCardNewsBinding
import com.beside.hackathon.databinding.FragmentHomeBinding
import com.beside.hackathon.presentation.view.home.HomeViewModel
import com.beside.hackathon.presentation.view.home.ViewPagerFragmentAdapter
import java.util.Calendar

class CardNewsFragment : Fragment() {
    private var _binding: FragmentCardNewsBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private val cardNewsViewModel: CardNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()

        _binding = FragmentCardNewsBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 설정
        binding.cardnewsRv.layoutManager = LinearLayoutManager(context)
        cardNewsViewModel.cardNewsUrls.observe(viewLifecycleOwner) { urls ->
            binding.cardnewsRv.adapter = CardNewsAdapter(urls)
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_cardNewsFragment_to_homeFragment)
        }

        // 데이터 로드
        cardNewsViewModel.loadCardNewsUrls()

    }
}