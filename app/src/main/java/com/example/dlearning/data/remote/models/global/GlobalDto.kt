package com.example.dlearning.data.remote.models.global


import com.google.gson.annotations.SerializedName

data class GlobalDto(
    @SerializedName("categories")
    val categories: Categories? = Categories(),
    @SerializedName("overall")
    val overall: Overall? = Overall()
)