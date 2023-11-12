package com.beside.hackathon.presentation.view.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import com.beside.hackathon.R
import com.beside.hackathon.core.utils.Colors.BG_GREY
import com.beside.hackathon.core.utils.Constant
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_H
import com.beside.hackathon.core.utils.TextStyles
import com.beside.hackathon.core.utils.TextStyles.BIG_TITLE_STYLE
import com.beside.hackathon.core.utils.TextStyles.CONTENT_TEXT1_STYLE
import com.beside.hackathon.core.utils.TextStyles.TITLE_TEXT_STYLE
import com.beside.hackathon.databinding.FragmentQuizSubmitBinding
import com.beside.hackathon.presentation.component.BackHandler
import com.beside.hackathon.presentation.component.CustomButton
import com.beside.hackathon.presentation.view.common.DefaultLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizSubmitFragment : Fragment() {
    private var _binding: FragmentQuizSubmitBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()
        _binding = FragmentQuizSubmitBinding.inflate(inflater, container, false)
        val root = binding.root

        val name = arguments?.getString("name")
        val count = arguments?.getInt("count")
        val total = arguments?.getInt("total")

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    QuizSubmitScreen(navController, name!!, count!!, total!!)
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


@Composable
fun QuizSubmitScreen(
    navController: NavController,
    name: String,
    count: Int,
    total: Int
){
    var backPressed by remember { mutableStateOf(false) }

    BackHandler(
        onBack = {
            // 뒤로가기 버튼을 누르면 수행할 작업을 여기에 정의합니다.
            // 예를 들어, 화면을 닫거나 특정 동작을 수행할 수 있습니다.
            backPressed = true
        }
    )

    if (backPressed) {
        navController.popBackStack(
            R.id.home_fragment,
            inclusive = false
        )
    }


    DefaultLayout(
        title = "퀴즈 풀기",
        backButtonOnClick = {
            navController.popBackStack(
                R.id.home_fragment,
                inclusive = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(DEFAULT_PADDING_H),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("제출이 완료되었습니다.", style = TITLE_TEXT_STYLE)
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        BG_GREY,
                        shape = RoundedCornerShape(Constant.BORDER_RADIUS)
                    ).padding(horizontal = DEFAULT_PADDING_H),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text("${name}님의 퀴즈 풀이 점수", style = TITLE_TEXT_STYLE)
                Spacer(modifier = Modifier.height(16.dp))
                Text("${count}/${total}", style = BIG_TITLE_STYLE)
                Spacer(modifier = Modifier.height(20.dp))
                Text("참 잘했어요:)", style = CONTENT_TEXT1_STYLE)
                Box(modifier = Modifier
                    .padding(bottom = 20.dp, top = 10.dp)
                    .background(BG_GREY)) {
                    CustomButton(onClick = {
                        navController.popBackStack(
                            R.id.home_fragment,
                            inclusive = false
                        )
                    }) {
                        Text("홈으로 가기", style = TextStyles.BUTTON_TEXT_STYLE)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun QuizSubmitScreenPreview(){
    val x = rememberNavController()
    QuizSubmitScreen(navController = x, name = "김민수", count = 3, total = 5)
}