package com.example.moviecolabs.service

import com.example.moviecolabs.model.DataUser
import com.example.moviecolabs.model.ResponseUserItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestfulUser {

    @GET("user")
    fun getAllUser(): Call<List<ResponseUserItem>>

    @POST("user")
    fun postUser(@Body request: DataUser): Call<ResponseUserItem>
}