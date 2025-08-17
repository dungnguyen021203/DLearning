package com.example.dlearning.presentation.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.dlearning.presentation.components.showToast
import com.example.dlearning.presentation.ui.pages.BookmarkPage
import com.example.dlearning.presentation.ui.pages.HomePage
import com.example.dlearning.presentation.ui.pages.ProfilePage
import com.example.dlearning.presentation.ui.pages.RankingPage

@SuppressLint("ContextCastToActivity")
@Composable
fun MainPage(modifier: Modifier = Modifier, navController: NavHostController) {
    val navItemList = listOf<NavItem>(
        NavItem("Home", Icons.Default.Home),
        NavItem("Ranking", Icons.Default.Leaderboard),
        NavItem("Bookmark", Icons.Default.Bookmarks),
        NavItem("Profile", Icons.Default.Person2)
    )

    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var backPressedTime by remember { mutableStateOf(0L) }

    BackHandler(enabled = true) {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            activity?.finish()
        } else {
            showToast(context = context, message = "Nhấn lần nữa để thoát")
            backPressedTime = System.currentTimeMillis()
        }
    }

    Scaffold (
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) {
        ContentScreen(modifier = Modifier.padding(it), selectedIndex, navController = navController)
    }
}

@Composable
fun ContentScreen(modifier: Modifier, index: Int, navController: NavHostController) {
    when(index) {
        0 -> HomePage(modifier, navController = navController)
        1 -> RankingPage(modifier, navController = navController)
        2 -> BookmarkPage(modifier, navController = navController)
        3 -> ProfilePage(modifier, navController = navController)
    }
}

data class NavItem(
    val label: String,
    val icon: ImageVector
)