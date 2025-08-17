package com.example.dlearning.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dlearning.presentation.ui.auth.ForgetPasswordScreen
import com.example.dlearning.presentation.ui.auth.LoginScreen
import com.example.dlearning.presentation.ui.auth.SignupScreen
import com.example.dlearning.presentation.ui.main.MainPage
import com.example.dlearning.presentation.ui.onboarding.OnboardingScreen
import com.example.dlearning.utils.ui.*

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(navController = navController)
        }
        composable(ROUTE_HOME) {
            MainPage(navController = navController)
        }
        composable(ROUTE_FORGET_PW) {
            ForgetPasswordScreen(navController = navController)
        }
        composable(ROUTE_ONBOARDING) {
            OnboardingScreen(navController = navController)
        }
    }
}