package com.example.dlearning.data.remote.models.global


import com.google.gson.annotations.SerializedName

data class Overall(
    @SerializedName("total_num_of_pending_questions")
    val totalNumOfPendingQuestions: Int? = null,
    @SerializedName("total_num_of_questions")
    val totalNumOfQuestions: Int? = null,
    @SerializedName("total_num_of_rejected_questions")
    val totalNumOfRejectedQuestions: Int? = null,
    @SerializedName("total_num_of_verified_questions")
    val totalNumOfVerifiedQuestions: Int? = null
)