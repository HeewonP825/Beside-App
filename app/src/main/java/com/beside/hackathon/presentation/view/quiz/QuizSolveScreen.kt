package com.beside.hackathon.presentation.view.quiz

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.beside.hackathon.core.utils.TextSyles.BUTTON_TEXT_STYLE
import com.beside.hackathon.presentation.component.CustomButton
import com.beside.hackathon.presentation.view.common.DefaultLayout
import com.beside.hackathon.presentation.viewmodel.quiz.QuizViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Composable
fun QuizSolveScreen(navController: NavController,viewModel: QuizViewModel){
    val quizState = viewModel.quiz.collectAsState()
    var page by remember { mutableIntStateOf(0) }
    val quizSubmitNumber = viewModel.quizSubmitNumbers.collectAsState()

    val composableScope = rememberCoroutineScope()

    DefaultLayout(
        title = "퀴즈 풀기",
        backButtonOnClick = {
            navController.popBackStack()
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Column {
                if(quizState.value.questions.size>1){
                    Text("Q${page}. ${quizState.value.questions[page].questionName}")

                    quizState.value.questions[page].options.forEach { opt->
                        CheckBoxRow(opt.content, quizSubmitNumber.value[page] == opt.optionId) {
                            viewModel.selectOption(page,opt.optionId)
                        }


                    }
                }
            }
            CustomButton(
                onClick = {
                    when(page){
                        quizState.value.questions.size-1 -> {
                            composableScope.launch {
                                val resp = viewModel.submitQuiz()
                                if(resp ==  null){
                                    Toast.makeText(navController.context,"풀지 않는 문제가 있습니다!",Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(navController.context,"${resp}",Toast.LENGTH_SHORT).show()

                                }
                            }
                        }
                        else -> {
                            page++
                        }
                    }
                }
            ) {
                when(page){
                    0 -> {
                        Spacer(modifier = Modifier
                            .size(1.dp)
                            .weight(1f))
                    }
                    else -> {

                        Text(
                            "이전 문제",
                            style = BUTTON_TEXT_STYLE,
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
                    "${page+1} / ${quizState.value.questions.size}",
                    style = BUTTON_TEXT_STYLE,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    "다음 문제",
                    style = BUTTON_TEXT_STYLE,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}


@Composable
fun CheckBoxRow(text: String, value: Boolean, onClick: (Any) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = value, onCheckedChange = onClick)
        ClickableText(
            text = AnnotatedString(text), onClick = onClick, modifier = Modifier.fillMaxWidth()
        )
    }
}
