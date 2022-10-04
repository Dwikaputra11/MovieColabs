package com.example.moviecolabs.model


import com.google.gson.annotations.SerializedName

data class ResponseUserItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)