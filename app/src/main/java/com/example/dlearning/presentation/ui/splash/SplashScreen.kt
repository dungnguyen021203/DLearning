package com.example.dlearning.presentation.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dlearning.presentation.components.AuthHeaderBookStack
import com.example.dlearning.presentation.viewmodel.AuthViewModel
import com.example.dlearning.utils.ui.ROUTE_HOME
import com.example.dlearning.utils.ui.ROUTE_LOGIN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(viewModel: AuthViewModel? = hiltViewModel(), navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(4000)
        val user = viewModel?.currentUser
        if (user != null) {
            navController.navigate(ROUTE_HOME) {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate(ROUTE_LOGIN) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AuthHeaderBookStack()
    }
}