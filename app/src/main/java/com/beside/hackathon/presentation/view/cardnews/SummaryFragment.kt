package com.beside.hackathon.presentation.view.cardnews

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beside.hackathon.R
import com.beside.hackathon.data.model.cardnews.SummaryUrls
import com.beside.hackathon.databinding.FragmentCardNewsBinding
import com.beside.hackathon.databinding.FragmentSummaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : Fragment() {
    private var _binding: FragmentSummaryBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private val summaryViewModel: SummaryViewModel by viewModels()
    private lateinit var webView: WebView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()

        _binding = FragmentSummaryBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        webView = view.findViewById(R.id.webview)
        // 웹뷰 설정
        webView.settings.javaScriptEnabled = true

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
           onBackPressed()
        }

        // ViewModel의 LiveData를 관찰합니다.
        summaryViewModel.summaryUrls.observe(viewLifecycleOwner) { summaryUrl ->
            // LiveData에서 반환된 단일 URL을 WebView에 로드합니다.
            webView.loadUrl(summaryUrl)
        }
        // 데이터 로드를 시작합니다.
        summaryViewModel.loadSummaryUrls() // 함수 이름이 loadSummaryUrl이라고 가정합니다.
    }

    fun onBackPressed(): Boolean {
        if (webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return false
    }

}