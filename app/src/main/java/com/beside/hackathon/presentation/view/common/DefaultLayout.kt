package com.beside.hackathon.presentation.view.common

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beside.hackathon.core.utils.TextStyles.TITLE_TEXT_STYLE

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DefaultLayout(
    title: String? = null,
    backButtonOnClick: () -> Unit = {},
    body: @Composable () -> Unit,
){
    Scaffold (
        topBar={
            title?.let {
                TopAppBar(
                    actions = {

                    },
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = backButtonOnClick) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = null)
                            }
                            Text(text = title, style = TITLE_TEXT_STYLE)
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
                )
            }
        }
    ){
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize().background(Color.White)) {
            body()
        }
    }
}