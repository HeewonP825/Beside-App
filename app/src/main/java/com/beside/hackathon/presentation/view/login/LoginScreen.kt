package com.beside.hackathon.presentation.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.beside.hackathon.R
import com.beside.hackathon.core.utils.TextStyles.BUTTON_TEXT_STYLE
import com.beside.hackathon.core.utils.TextStyles.CONTENT_TEXT3_STYLE
import com.beside.hackathon.core.utils.TextStyles.PYEONG_CONTENT1_STLYE
import com.beside.hackathon.core.utils.TextStyles.PYEONG_CONTENT2_STLYE
import com.beside.hackathon.core.utils.TextStyles.PYEONG_TITLE_STLYE
import com.beside.hackathon.presentation.component.CustomButton
import com.beside.hackathon.presentation.view.common.DefaultLayout

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel){

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLogin = userViewModel.isLogin.value
    if(isLogin){
        navController.navigate(R.id.action_login_fragment_to_home_fragment)
    }
    DefaultLayout {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 45.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            TopPart()
            Spacer(modifier = Modifier.height(20.dp))
            MiddlePart(
                id = id,
                password = password,
                onIdChanged = { id = it },
                onPasswordChanged = { password = it },
                onKeyboardAction = {
                    userViewModel.login(id, password)
                }
            )
            Spacer(modifier = Modifier.height(60.dp))
            BottomPart(
                onClick = {
                    userViewModel.login(id, password)
                },
                signUpClick = {
                    navController.navigate(R.id.action_login_fragment_to_signUpFragment)
                }
            )
            Spacer(modifier = Modifier.height(85.dp))
        }
    }
}

@Composable
internal fun TopPart(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_icon),
            contentDescription = "Be:SIDE 로고",
            modifier = Modifier.size(255.dp)
        )
        Text("언제나 당신곁에,",
            style = PYEONG_CONTENT1_STLYE)
        Text(
            "Be:SIDE",
            style = PYEONG_TITLE_STLYE
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "간단하게 로그인하고\n다양한 서비스를 이용해 보세요.",
            style = PYEONG_CONTENT2_STLYE,
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MiddlePart(
    id:String,
    password: String,
    onIdChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onKeyboardAction: () -> Unit,
){
    val focusManager = LocalFocusManager.current
    Column {
        TextField(
            label = { Text("아아디",style = PYEONG_CONTENT2_STLYE) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
            singleLine = true,
            value = id,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Next) }),
            onValueChange = onIdChanged
        )
        TextField(

            label = { Text("비밀번호", style = PYEONG_CONTENT2_STLYE) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
            singleLine = true,
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onKeyboardAction()
            }),
            onValueChange = onPasswordChanged
        )
    }
}

@Composable
internal fun BottomPart(
    onClick: () -> Unit,
    signUpClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("아직 계정 없으신가요? 회원가입",style =CONTENT_TEXT3_STYLE.copy(
            color = Color(0xFF707070)
        ),
            modifier = Modifier.clickable {
                signUpClick()
            })
        Spacer(modifier = Modifier.height(12.dp))
        CustomButton(onClick = onClick) {
            Text("로그인", style = BUTTON_TEXT_STYLE)
        }

    }
}
