package com.example.dlearning.presentation.ui.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.dlearning.R

sealed class OnboardingPage (
    @DrawableRes val image: Int,
    val title: String,
    @StringRes val description: Int
) {
    object FirstPage: OnboardingPage(
        image = R.drawable.firstpage,
        title = "Learn Anytime, Anywhere",
        description = R.string.first_page_onboarding
    )
    object SecondPage: OnboardingPage(
        image = R.drawable.secondpage,
        title = "Interactive & Engaging Lessons",
        description = R.string.first_page_onboarding
    )
    object ThirdPage: OnboardingPage(
        image = R.drawable.thirdpage,
        title = "Track Your Progress & Achieve Goals",
        description = R.string.first_page_onboarding
    )
}