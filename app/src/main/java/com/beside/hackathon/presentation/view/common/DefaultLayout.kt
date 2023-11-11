package com.beside.hackathon.presentation.view.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beside.hackathon.core.utils.TextSyles.TITLE_TEXT_STYLE

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DefaultLayout(
    title: String? = null,
    body: @Composable () -> Unit
){
    Scaffold (
        topBar={
            title?.let {
                TopAppBar(
                    title = {
                        Text(text = title, style = TITLE_TEXT_STYLE)
                    }
                )
            }
        }
    ){
        Box(modifier = Modifier.padding(it)) {
            body()
        }
    }
}