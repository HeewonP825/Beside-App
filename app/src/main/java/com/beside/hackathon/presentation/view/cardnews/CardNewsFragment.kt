package com.beside.hackathon.presentation.view.cardnews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
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

        binding.cardnewsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                try {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()

                    val endHasBeenReached = lastVisible + 1 >= totalItemCount
                    if (totalItemCount > 0 && endHasBeenReached) {
                        // 스크롤이 끝에 도달했을 때 버튼 상태 변경
                        binding.readBtn.apply {
                            isEnabled = true
                            //setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_cardnews_read_btn))// 색상 변경
                            setBackgroundColor(ContextCompat.getColor(context, R.color.mainColor)) // 색상 변경
                        }
                        binding.readBtn.setOnClickListener {
                            findNavController().navigate(R.id.action_cardNewsFragment_to_homeFragment)
                        }
                    }
                } catch (e: Exception) {
                    Log.e("CardNewsFragment", "Scroll error: ${e.message}", e)
                }
            }
        })
    }
}