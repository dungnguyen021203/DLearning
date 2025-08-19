package com.example.dlearning.utils.constants

object CategoryConstants {
    private val categoryMap = mapOf(
        9 to "General Knowledge",
        10 to "Books",
        11 to "Film",
        12 to "Music",
        13 to "Musicals & Theatres",
        14 to "Television",
        15 to "Video Games",
        16 to "Board Games",
        17 to "Science & Nature",
        18 to "Computers",
        19 to "Mathematics",
        20 to "Mythology",
        21 to "Sports",
        22 to "Geography",
        23 to "History",
        24 to "Politics",
        25 to "Art",
        26 to "Celebrities",
        27 to "Animals",
        28 to "Vehicles",
        29 to "Comics",
        30 to "Gadgets",
        31 to "Japanese Anime & Manga",
        32 to "Cartoon & Animations"
    )

    fun getCategoryById(id: Int): String {
        return categoryMap[id] ?: "Unknown"
    }
}

