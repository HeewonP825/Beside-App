package com.beside.hackathon.presentation.view.quiz

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.beside.hackathon.R
import com.beside.hackathon.core.utils.Colors.BG_GREY
import com.beside.hackathon.core.utils.Colors.BUTTON_YELLOW
import com.beside.hackathon.core.utils.Constant.BORDER_RADIUS
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_H
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_V
import com.beside.hackathon.core.utils.TextStyles.BUTTON_TEXT_STYLE
import com.beside.hackathon.core.utils.TextStyles.TITLE_TEXT2_STYLE
import com.beside.hackathon.data.model.quiz.Answer
import com.beside.hackathon.data.model.quiz.Question
import com.beside.hackathon.presentation.component.CustomButton
import com.beside.hackathon.presentation.view.common.DefaultLayout
import kotlinx.coroutines.launch


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
            if(quizState.value.questions.isNotEmpty()){
                QuizBox(
                    page = page,
                    questions = quizState.value.questions,
                    quizSubmitAnswers = quizSubmitNumber.value,
                    onClick = {
                        Log.d("QuizSolveScreen", "질문id: ${quizState.value.questions[page].questionId}, 선택id: $it")
                        viewModel.selectOption(quizState.value.questions[page].questionId, it)
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                onClick = {
                    when(page){
                        quizState.value.questions.size-1 -> {
                            composableScope.launch {
                                val resp = viewModel.submitQuiz()
                                if(resp ==  null){
                                    Toast.makeText(navController.context,"풀지 않는 문제가 있습니다!",Toast.LENGTH_SHORT).show()
                                }else{
                                    val bundle = bundleOf(
                                        "name" to resp.nickname,
                                        "count" to resp.correctAnswerCount,
                                        "total" to resp.totalQuestionCount
                                    )
                                    navController.navigate(R.id.action_quizSolveFragment_to_quizSubmitFragment, bundle)
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
                            "이전으로",
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
                    "다음으로",
                    style = BUTTON_TEXT_STYLE,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}

@Composable
fun QuizBox(page: Int, questions: List<Question>, quizSubmitAnswers: List<Answer?>, onClick: (Int) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                BG_GREY,
                shape = RoundedCornerShape(BORDER_RADIUS)
            )
            .padding(vertical = DEFAULT_PADDING_V, horizontal = DEFAULT_PADDING_H)
    ){
        Text(
            "Q${page}. ${questions[page].questionName}",
            style = TITLE_TEXT2_STYLE
        )

        Spacer(modifier = Modifier.height(20.dp))
        questions[page].options.forEach { opt->
            CheckBoxRow(opt.content, quizSubmitAnswers[page]?.optionId == opt.optionId, opt.optionId ) {
                onClick(opt.optionId)
                Log.d("QuizSolveScreen", "opt: ${opt.optionId}")
                Log.d("QuizSolveScreen", "q : ${questions[page]}, submit: ${quizSubmitAnswers[page]}")
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Preview
@Composable
fun preview(){
//    QuizBox(
//        page = 0,
//        questions = listOf(
//            Question(
//                questionId = 1,
//                questionName = "테스트",
//                options = listOf(
//                    Option(
//                        optionId = 1,
//                        content = "테스트1"
//                    ),
//                    Option(
//                        optionId = 2,
//                        content = "테스트2"
//                    )
//                )
//            )
//        ),
//        quizSubmitNumber = listOf(1),
//        onClick = {
//
//        }
//    )
}

@Composable
fun CheckBoxRow(text: String, value: Boolean, id: Int, onClick: (Any) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)) {
//        Checkbox(
//            checked = value,
//            onCheckedChange = onClick,
//            colors = CheckboxDefaults.colors(
//                checkedColor = BUTTON_YELLOW
//            )
//        )
        RadioButton(
            modifier = Modifier.size(12.dp),
            selected = value,
            onClick = {
                onClick(id)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = BUTTON_YELLOW
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        ClickableText(
            text = AnnotatedString(text), onClick = onClick, modifier = Modifier.fillMaxWidth()
        )
    }
}
