package com.android.details_data.entity

data class RepoDetail(
    val id : String,
    val name : String,
    val fullName : String,
    val ownerImageUrl : String,
    val htmlUrl : String,
    val description : String,
    val visibility : String,
    val isPrivate : String,
)
