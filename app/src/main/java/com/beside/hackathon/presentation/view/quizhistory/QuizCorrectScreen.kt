package com.beside.hackathon.presentation.view.quizhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.beside.hackathon.core.utils.Colors
import com.beside.hackathon.core.utils.Constant
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_H
import com.beside.hackathon.core.utils.TextStyles
import com.beside.hackathon.data.model.quiz.Question
import com.beside.hackathon.data.model.quizhistory.CorrectQuestion
import com.beside.hackathon.presentation.component.CustomButton
import com.beside.hackathon.presentation.view.common.DefaultLayout
import com.beside.hackathon.presentation.view.quiz.CheckBoxRow
import com.beside.hackathon.presentation.view.quiz.QuizBox

@Composable
fun QuizCorrectScreen(navController: NavController, quizHistoryViewModel: QuizHistoryViewModel, id:Long) {
    val correctQuizList = quizHistoryViewModel.correctQuiz.collectAsState()
    var page by remember { mutableIntStateOf(0) }
    DefaultLayout(
        title = correctQuizList.value?.quizName ?: "퀴즈결과",
        backButtonOnClick = {
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = DEFAULT_PADDING_H),
        ) {

            if(correctQuizList.value != null){
                QuizCorrectBox(
                    page+1, correctQuizList.value!!.questions[page]
                )
            }

            CustomButton(onClick = {
                if(page != correctQuizList.value?.questions?.size?.minus(1)){
                    page++
                }
            }) {
                when(page){
                    0 -> {
                        Spacer(modifier = Modifier
                            .size(1.dp)
                            .weight(1f))
                    }
                    else -> {

                        Text(
                            "이전으로",
                            style = TextStyles.BUTTON_TEXT_STYLE,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    page--
                                },
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Text(
                    "${page+1} / ${correctQuizList.value?.questions?.size ?: 0}",
                    style = TextStyles.BUTTON_TEXT_STYLE,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    "다음으로",
                    style = TextStyles.BUTTON_TEXT_STYLE,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}


@Composable
fun QuizCorrectBox(page:Int, question: CorrectQuestion){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Colors.BG_GREY,
                shape = RoundedCornerShape(Constant.BORDER_RADIUS)
            )
            .padding(vertical = Constant.DEFAULT_PADDING_V, horizontal = DEFAULT_PADDING_H)
    ){
        Text(
            "Q${page+1}. ${question.questionName}",
            style = TextStyles.TITLE_TEXT2_STYLE
        )

        question.options.forEach { opt->
            CheckBoxCorrectRow(
                opt.content,
                opt.optionId == question.answerOptionId,
                opt.optionId == question.selectedOptionId
            )
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}


@Composable
fun CheckBoxCorrectRow(text: String, ok: Boolean,no: Boolean ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = ok || no,
            onCheckedChange = {},
            colors = CheckboxDefaults.colors(
                //TODO: 색상 변경
                checkedColor = Colors.BUTTON_YELLOW
            )
        )
        ClickableText(
            text = AnnotatedString(text), onClick = {}, modifier = Modifier.fillMaxWidth()
        )
    }
}