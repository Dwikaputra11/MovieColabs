package com.example.moviecolabs.service

import com.example.moviecolabs.data.Film
import com.example.moviecolabs.model.ResponseFilmItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("film")
    fun getAllFilm(): Call<List<ResponseFilmItem>>

    @PUT("film/{id}")
    fun updateFilm(@Path("id") id: String, @Body film: Film): Call<ResponseFilmItem>
}