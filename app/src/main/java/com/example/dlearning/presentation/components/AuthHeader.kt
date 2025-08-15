package com.example.dlearning.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.dlearning.R

@Composable
fun AuthHeader(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome))

    val progress by animateLottieCompositionAsState(
        isPlaying = true,
        composition = composition,
        speed = 0.7f
    )

    LottieAnimation(
        modifier = modifier.size(250.dp),
        composition = composition,
        progress = {progress}
    )
}

@Composable
fun AuthHeaderBookStack(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.book_stack))

    val progress by animateLottieCompositionAsState(
        isPlaying = true,
        composition = composition,
        speed = 0.7f
    )

    LottieAnimation(
        modifier = modifier.size(250.dp),
        composition = composition,
        progress = {progress}
    )
}