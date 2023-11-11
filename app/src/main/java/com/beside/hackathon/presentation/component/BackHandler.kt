package com.beside.hackathon.presentation.component

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun BackHandler(
    enabled: Boolean = true,
    onBack: () -> Unit
) {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current
    val callback = rememberUpdatedState(onBack)

    DisposableEffect(callback) {
        val onBackPressedCallback = object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    callback.value()
                }
            }
        }

        dispatcher?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)

        onDispose {
            onBackPressedCallback.remove()
        }
    }
}