package com.beside.hackathon.presentation.view.quizhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.beside.hackathon.R
import com.beside.hackathon.core.utils.Colors.BG_GREY
import com.beside.hackathon.core.utils.Constant.BORDER_RADIUS
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_H
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_V
import com.beside.hackathon.core.utils.TextStyles.CONTENT_SMALL1_STYLE
import com.beside.hackathon.core.utils.TextStyles.CONTENT_SMALL2_STYLE
import com.beside.hackathon.core.utils.TextStyles.MEDIUMN_TITLE_STYLE
import com.beside.hackathon.core.utils.TextStyles.TITLE_TEXT4_STYLE
import com.beside.hackathon.data.model.quizhistory.QuizHistory
import com.beside.hackathon.presentation.view.common.DefaultLayout

@Composable
fun QuizHistoryScreen(navController: NavController, viewModel: QuizHistoryViewModel) {
    val historyList = viewModel.history.collectAsLazyPagingItems()
    DefaultLayout(
        title = "역대 퀴즈 기록",
        backButtonOnClick = {
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = DEFAULT_PADDING_H),
        ) {
            LazyColumn {
                items(historyList.itemCount) { index ->
                    historyList[index]?.let { history ->
                        QuizHistoryItem.fromModel(
                            model = history,
                            onClick = {
                                val bundle = bundleOf(
                                    "id" to history.quizId,
                                )
                                navController.navigate(R.id.action_quizHistoryFragment_to_quizCorrectFragment, bundle)
                            }
                        )
                        Spacer(modifier = Modifier.height(DEFAULT_PADDING_V))
                    }
                }
            }
        }

    }
}

object QuizHistoryItem

@Composable
fun QuizHistoryItem.fromModel(model: QuizHistory, onClick: () -> Unit) {
    QuizHistoryItemBase(
        title = model.subject,
        startDate = model.startDate,
        endDate = model.endDate,
        correctCount = model.correctCount,
        wrongCount = model.wrongCount,
        quizPercent = model.answerLate,
        onClick = onClick
    )
}

@Composable
fun QuizHistoryItemBase(
    title: String,
    startDate: String,
    endDate: String,
    correctCount: Int,
    wrongCount: Int,
    quizPercent: Double,
    onClick: () -> Unit
) {

    Column (
        modifier = Modifier
            .background(
                color = BG_GREY,
                shape = RoundedCornerShape(BORDER_RADIUS)
            )
            .padding(DEFAULT_PADDING_V),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = TITLE_TEXT4_STYLE)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "틀린 문제 확인하기",
                style =CONTENT_SMALL1_STYLE,
                modifier = Modifier.clickable {
                    onClick()
                }
            )
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "${startDate} ~ ${endDate}",
            style = CONTENT_SMALL1_STYLE.copy(
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            WhiteBox(title = "맞힌 문제수", content = correctCount.toString())
            WhiteBox(title = "틀린 문제수", content = wrongCount.toString())
            WhiteBox(title = "퀴즈 정답률", content = "${quizPercent}%")
        }


    }

}

@Composable
fun WhiteBox(title:String, content: String){
    Column(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(BORDER_RADIUS)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(title, style = CONTENT_SMALL2_STYLE)
        Text(content,style =MEDIUMN_TITLE_STYLE)
    }
}


@Preview
@Composable
fun previewQuizHistoryScreen() {
    val x = rememberNavController()
    //QuizHistoryScreen(navController = x)
}