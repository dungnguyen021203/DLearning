package com.example.dlearning.presentation.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dlearning.presentation.viewmodel.AuthViewModel
import com.example.dlearning.utils.ui.ROUTE_HOME
import com.example.dlearning.utils.ui.ROUTE_LOGIN

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Signout", modifier = Modifier.clickable {
            viewModel.signOut()
            navController.navigate(ROUTE_LOGIN) {
                popUpTo(ROUTE_HOME) { inclusive = true }
            }
        })
    }
}