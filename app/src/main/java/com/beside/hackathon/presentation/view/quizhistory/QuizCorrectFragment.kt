package com.beside.hackathon.presentation.view.quizhistory


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.beside.hackathon.databinding.FragmentQuizCorrectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizCorrectFragment : Fragment() {
    private var _binding: FragmentQuizCorrectBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private val quizHistoryViewModel: QuizHistoryViewModel by viewModels()
    private var quizId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()
        _binding = FragmentQuizCorrectBinding.inflate(inflater, container, false)
        val root = binding.root

        quizId = arguments?.getLong("id")

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    QuizCorrectScreen(navController,quizHistoryViewModel,quizId!!)
                }
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizHistoryViewModel.getCorrectQuiz(quizId!!)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}