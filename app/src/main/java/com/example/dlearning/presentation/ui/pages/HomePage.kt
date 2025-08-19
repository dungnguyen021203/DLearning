package com.example.dlearning.presentation.ui.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.dlearning.presentation.viewmodel.AuthViewModel
import com.example.dlearning.presentation.viewmodel.GlobalViewModel
import com.example.dlearning.utils.ui.ROUTE_HOME
import com.example.dlearning.utils.ui.ROUTE_LOGIN
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dlearning.common.Resource
import com.example.dlearning.presentation.components.showToast
import com.example.dlearning.utils.ui.LoadingCircle
import com.example.dlearning.utils.ui.LoadingCircleWholePage

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel(),
    globalViewModel: GlobalViewModel = hiltViewModel()
) {

    val globalState by globalViewModel.globalState.collectAsState()
    val context = LocalContext.current

    globalState.let {
        when(it) {
            is Resource.Failure -> {
                LaunchedEffect(globalState) {
                    showToast(context = context, message = it.exception.message.toString())
                }
            }
            is Resource.Loading -> LoadingCircleWholePage()
            is Resource.Success<*> -> {
                val global = (globalState as Resource.Success).data
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "${global.totalNumOfQuestions}", modifier = Modifier.clickable {
                        viewModel.signOut()
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(ROUTE_HOME) { inclusive = true }
                        }
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(autoFocus: Boolean = false) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    if (autoFocus) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.systemBars.asPaddingValues()
            )
            .padding(10.dp),
        contentPadding = PaddingValues(
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 30.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Welcome
        item {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Welcome, Dung Nguyen",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                        Text(text = "What do you want to learn today?", color = Color.Gray)
                    }
                    Card(
                        shape = RoundedCornerShape(30.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(45.dp)
                                .background(Color.Blue, shape = RoundedCornerShape(30.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = "", tint = Color.White)
                        }
                    }
                }
            }
        }

        // Search Bar
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { it }, // productViewModel::onQueryChange, // { newValue -> productViewModel.onQueryChange(newValue) }
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(10.dp))
                        .focusRequester(focusRequester),
                    placeholder = {
                        Text(text = "Search for quizzes", fontSize = 16.sp)
                    },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Blue)
                    },
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {
//                        if (query.isNotBlank()) {
//                            IconButton(onClick = {
//                                productViewModel.onQueryChange("")
//                                focusManager.clearFocus()
//                            }) {
//                                Icon(Icons.Default.Clear, contentDescription = "Clear")
//                            }
//                        }
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = "Clear",
                            tint = Color.Blue
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
//                    keyboardActions = KeyboardActions(
//                        onDone = {
//                            productViewModel.performSearch(query = query)
//                            navController.navigate(ROUTE_SEARCH_RESULT + "${Uri.encode(query)}")
//                        }
//                    )
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent
                    )
                )

//                if (suggestions.isNotEmpty()) {
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 4.dp),
//                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                    ) {
//                        Column {
//                            suggestions.forEach { suggestion ->
//                                Text(
//                                    text = suggestion,
//                                    Modifier.fillMaxWidth().clickable {
//                                        productViewModel.performSearch(suggestion)
//                                        navController.navigate(ROUTE_SEARCH_RESULT + "${Uri.encode(suggestion)}")
//                                    }.padding(12.dp)
//                                )
//                            }
//                        }
//                    }
//                }
            }
        }

        // Categories
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Categories",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFC0D8EB),
                        contentColor = Color.Blue
                    )
                ) {
                    Text(
                        text = "See all",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(7.dp)
                            .clickable {
                            })
                }
            }
        }
        val list = listOf<String>(
            "Science",
            "Programming",
            "Mathematics",
            "Mythology",
            "Sports",
            "Geography"
        )
        items(list.chunked(3)) { rowItems ->
            Row {
                rowItems.forEach { cat ->
                    CategoryCard(
                        category = cat,
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }

        // Custom Quizzes
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Custom Quizzes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFC0D8EB),
                        contentColor = Color.Blue
                    )
                ) {
                    Text(
                        text = "See all",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(7.dp)
                            .clickable {
                            })
                }
            }
        }
        item {
            CustomQuizzesCard(
                modifier = Modifier,
                userName = "Dung Nguyen",
                time = "30 minutes",
                questionCount = 10,
                topic = "Cau truc Du lieu",
                imageUrl = "https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/3138c8f0-5929-48d7-8c58-9126f53d8463/NIKE+INTERACT+RUN+EASYON.png"
            )

        }
    }
}

@Composable
fun CategoryCard(category: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clickable {
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFC0D8EB)
                )
            ) {
                Icon(
                    Icons.Default.Code,
                    contentDescription = category,
                    tint = Color.Blue,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Text(text = category, fontWeight = FontWeight.Medium, fontSize = 12.sp)
        }
    }
}

@Composable
fun CustomQuizzesCard(
    modifier: Modifier = Modifier,
    userName: String,
    time: String,
    questionCount: Int,
    topic: String,
    imageUrl: String? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { /* xử lý khi click */ },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Nếu có ảnh thì show
            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                // Avatar + tên + thời gian
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                                .padding(4.dp),
                            tint = Color.White
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(userName, fontWeight = FontWeight.Bold)
                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Icon(Icons.Default.AccessTime, contentDescription = "", modifier = Modifier.size(14.dp))
                                Text(time, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                            }
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "rating",
                            tint = Color(0xFFFFD700), // vàng
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "4.5",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                // Chủ đề
                Text(
                    text = topic,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Build IOS and Android apps with a single codebase to test your knowledge with just 10 questions",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(Modifier.height(4.dp))

                // Số câu hỏi
                Text(
                    text = "$questionCount questions",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Blue
                )
            }
        }
    }
}